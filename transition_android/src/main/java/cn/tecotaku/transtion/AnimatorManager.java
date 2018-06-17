package cn.tecotaku.transtion;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by HakureiSino on 2016/6/5 0005.
 * 动画线程主要控制器
 */

public class AnimatorManager {

    public static CopyOnWriteArrayList<ViewContainer> queue = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<MaskContainer> maskQueue = new CopyOnWriteArrayList<>();
    public static Thread animator;
    public static Activity activity;
    public static Iterator<ViewContainer> iterator;
    public static boolean isRegistActivityFront = false;
    public static HoverMask maskFrame;

    private final static int DEALY = 16;

    public static Runnable run = new Runnable() {
        @Override
        public void run() {
            long lasttime = -1,curr;
            int delta=DEALY;
            while ((queue.size()!=0)&&
                    isRegistActivityFront&&activity!=null&&!activity.isFinishing()){
                curr = System.currentTimeMillis();
                if (lasttime == -1) delta = DEALY;
                else {
                    try {
                        long temp = curr - lasttime;
                        if (temp < DEALY) {
                            Thread.sleep(DEALY - temp);
                            delta = DEALY;
                        }
                        else delta = (int)temp;
                    }catch(Exception e) {
                        e.printStackTrace();
                        Log.e("Animator", "Animator Thrad Crashed!");
                    }
                }
                lasttime = curr;
                if (delta > DEALY) Log.i("Animator", delta+"MILLSECONDS");
                iterator = queue.iterator();
                while(iterator.hasNext()){
                    ViewContainer vc = iterator.next();
                    vc.refresh(delta);
                }
            }

            if (activity==null)
                Log.e("Error", "Not bind to a Activity yet, please use method AnimatorManager.registActivity(Activity activity) first");

        }
    };


    /*
    *   Inject animation mask to top of view Tree

    private static void injectMaskFrame (Activity a) {
        maskFrame = new HoverMask(a);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        FrameLayout root = (FrameLayout)(a.getWindow().getDecorView().findViewById(android.R.id.content));
        root.addView(maskFrame, params);

    }
*/
    public static Handler handler;
    /*
     * 注册动画activity
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
        //injectMaskFrame(ac);
    }
}
