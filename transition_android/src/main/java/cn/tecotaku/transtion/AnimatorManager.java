package cn.tecotaku.transtion;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by HakureiSino on 2016/6/5 0005.
 * 动画线程主要控制器
 */

public class AnimatorManager {

    public static CopyOnWriteArrayList<ViewContainer> queue = new CopyOnWriteArrayList<>();
    public static Thread animator;
    public static Activity activity;
    public static Iterator<ViewContainer> iterator;
    public static boolean isRegistActivityFront = false;

    public static Runnable run = new Runnable() {
        @Override
        public void run() {
            long lasttime = -1,curr;
            int delta=30;
            while (queue.size()!=0&&isRegistActivityFront&&activity!=null&&!activity.isFinishing()){
                curr = System.currentTimeMillis();
                if (lasttime == -1) delta = 30;
                else {
                    try {
                        long temp = curr - lasttime;
                        if (temp < 30) {
                            Thread.sleep(30 - temp);
                            delta = 30;
                        }
                        else delta = (int)temp;
                    }catch(Exception e) {
                        e.printStackTrace();
                        Log.e("Animator", "Animator Thrad Crashed!");
                    }
                }
                lasttime = curr;
                if (delta > 30) Log.i("Animator", delta+"MILLSECONDS");
                iterator = queue.iterator();
                while(iterator.hasNext()){
                    ViewContainer vc = iterator.next();
                    vc.refresh(delta);
                }
            }
            //LogUtil.i("activity:"+(activity==null?"null":"not")+" activity state:"+(activity.isFinishing()?"finishing":"not"));
            if (activity==null) Log.e("Error", "Not bind to a Activity yet, please use method AnimatorManager.registActivity(Activity activity) first");

        }
    };

    public static Handler handler;
    /*
     *
     */
    public static void registActivity (Activity ac){
        activity = ac;
        isRegistActivityFront = true;
        activity.getApplication().registerActivityLifecycleCallbacks(new ActivityLifeCallbacks());
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x001){
                    ((View)msg.obj).setBackgroundColor(msg.arg1);
                }
            }
        };
    }
}
