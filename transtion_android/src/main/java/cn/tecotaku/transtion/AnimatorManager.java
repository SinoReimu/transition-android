package cn.tecotaku.transtion;

import android.util.Log;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/6/5 0005.
 */
public class AnimatorManager {

    public static ArrayList<ViewContainer> queue = new ArrayList<>();
    public static Thread animator = new Thread(new Runnable() {
        @Override
        public void run() {
            long lasttime = -1,curr;
            int delta=30;
            while (queue.size()!=0){
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
                if (delta > 30) Log.i("Animator", delta/30+"frame missed");

                for (ViewContainer l: queue) l.refresh(delta);
            }
        }
    });


}
