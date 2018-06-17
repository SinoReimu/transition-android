package cn.tecotaku.transtion;

import android.view.View;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.tecotaku.transtion.interpolator.Interpolator;
import cn.tecotaku.transtion.interpolator.LinearInpolator;
import cn.tecotaku.transtion.utils.PropertyUtil;

/**
 * Created by HakureiSino on 2016/6/4 0004.
 * View封装
 */

public class ViewContainer {

    public static final String ALPHA = "alpha";
    public static final String SCALEX = "scaleX";
    public static final String SCALEY = "scaleY";
    public static final String ROTATIONX = "rotationX";
    public static final String ROTATIONY = "rotationY";
    public static final String TRANSLATEX = "translateX";
    public static final String TRANSLATEY = "translateY";
    public static final String X = "X";
    public static final String Y = "Y";
    public static final String BACKGROUNDCOLOR = "backgroundColor";

    ConcurrentHashMap<String, PropertyContainer> hash;
    MaskContainer mask;
    View mView;
    AnimatorListener listener;

    public ViewContainer (View view){
        mView = view;
        hash = new ConcurrentHashMap<>();
    }

    public void setMask(int color, int toRadius, int time, Interpolator interpolator) {
        if (mask == null) {
            mask = new MaskContainer(mView, 1, 0,toRadius, time, color);
        } else {
            mask.startOffset = mask.currentOffset;
            mask.duration = time;
            mask.hasUse = 0;
            mask.endOffset = 1;
        }

    }

    public void setProperty(String key, float number){
        setProperty(key, number, 300);
    }

    public void setProperty (String key, float number, int time) {
        setProperty(key, number, time, new LinearInpolator());
    }

    public void  setProperty(String key, float number, int time, Interpolator interpolator) {
        if(!hash.containsKey(key)){
            PropertyContainer a = new PropertyContainer(PropertyUtil.getProperty(mView, key), number, time, interpolator);
            hash.put(key, a);
        }else{
            PropertyContainer a = hash.get(key);
            a.startValue = a.currentValue;
            a.endValue = number;
            a.duration = time;
            a.hasUse = 0;
        }
        if (!AnimatorManager.queue.contains(this)) AnimatorManager.queue.add(this);
        if (AnimatorManager.animator == null ||
                !AnimatorManager.animator.isAlive()) {
            AnimatorManager.animator = new Thread(AnimatorManager.run);
            AnimatorManager.animator.start();
        }
        if (listener!=null) listener.onStart();
    }

    protected void refresh(int h){
        /*refresh properties*/
        Iterator iter = hash.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String k = (String)entry.getKey();
            float value = ((PropertyContainer)entry.getValue()).refresh(k, h);
            if(value == -1) {
                iter.remove();
                if (hash.size() == 0) AnimatorManager.queue.remove(this);
                if (listener!=null) listener.onEnd();
            }
            else if(AnimatorManager.isRegistActivityFront) PropertyUtil.setProperty(mView, k, value);
            mView.postInvalidate();
        }
    }

    public void setAnimatorListener (AnimatorListener a){
        listener = a;
    }
}
