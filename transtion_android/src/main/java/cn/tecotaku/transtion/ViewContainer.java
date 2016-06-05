package cn.tecotaku.transtion;

import android.view.View;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.tecotaku.transtion.utils.PropertyUtil;

/**
 * Created by Administrator on 2016/6/4 0004.
 */
public class ViewContainer {

    HashMap<String, PropertyContainer> hash;
    View mView;

    public ViewContainer (View view){
        mView = view;
        hash = new HashMap<>();
    }

    public void  setProperty(String key, float number){
        setProperty(key, number, 300);
    }

    public void  setProperty(String key, float number, int time){
        if(!hash.containsKey(key)){
            PropertyContainer a = new PropertyContainer(PropertyUtil.getProperty(mView, key), number, time);
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
    }

    public void refresh(int h){
        Iterator iter = hash.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String k = (String)entry.getKey();
            float value = ((PropertyContainer)entry.getValue()).refresh(h);
            if(value == -1) {
                hash.remove(k);
                if (hash.size() == 0) AnimatorManager.queue.remove(this);
            }
            else PropertyUtil.setProperty(mView, k, value);
            mView.postInvalidate();
        }
    }
}
