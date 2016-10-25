package cn.tecotaku.transtion;

import android.view.View;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.tecotaku.transtion.interpolator.Interpolator;
import cn.tecotaku.transtion.interpolator.LinearInpolator;
import cn.tecotaku.transtion.utils.PropertyUtil;

/**
 * Created by HakureiSino on 2016/6/4 0004.
 * View封装
 */
public class ViewContainer {

    HashMap<String, PropertyContainer> hash;
    View mView;
    AnimatorListener listener;

    public ViewContainer (View view){
        mView = view;
        hash = new HashMap<>();
    }

    public void  setProperty(String key, float number){
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

    public void refresh(int h){
        Iterator iter = hash.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String k = (String)entry.getKey();
            float value = ((PropertyContainer)entry.getValue()).refresh(k, h);
            if(value == -1) {
                iter.remove();
                if (hash.size() == 0) AnimatorManager.iterator.remove();
                if (listener!=null) listener.onEnd();
            }
            else  PropertyUtil.setProperty(mView, k, value);
            mView.postInvalidate();
        }
    }

    public void setAnimatorListener (AnimatorListener a){
        listener = a;
    }
}
