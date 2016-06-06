package cn.tecotaku.transtion;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import cn.tecotaku.transtion.utils.LogUtil;
import cn.tecotaku.transtion.utils.PropertyUtil;


/**
 * Created by Administrator on 2016/6/5 0005.
 */
public class AnimatorManager {

    public static ArrayList<ViewContainer> queue = new ArrayList<>();

    public static Runnable run = new Runnable() {
        @Override
        public void run() {
            long lasttime = -1,curr;
            int delta=30;
            while (queue.size()!=0&&activity!=null&&!activity.isFinishing()){
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

                for (ViewContainer l: queue) l.refresh(delta);
            }
            //LogUtil.i("activity:"+(activity==null?"null":"not")+" activity state:"+(activity.isFinishing()?"finishing":"not"));
            if (activity==null) Log.e("Error", "Not bind to a Activity yet, please use method AnimatorManager.registActivity(Activity activity) first");

        }
    };
    public static Thread animator;
    public static Activity activity;
    public static void registActivity (Activity ac){
        activity = ac;
    }
}
