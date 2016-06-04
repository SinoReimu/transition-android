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

    public ViewContainer (View view,int type){
        mView = view;
        hash = new HashMap<>();
    }

    public void  setProperty(String key, double number){
        setProperty(key, number, 300);
    }

    public void  setProperty(String key, double number, int time){
        if(!hash.containsKey(key)){

        }
    }

    public void refresh(int h){
        Iterator iter = hash.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String k = (String)entry.getKey();
            double value = ((PropertyContainer)entry.getValue()).refresh(h);
            if(value == -1) {
                hash.remove(k);
                if (hash.size() == 0) AnimatorManager.queue.remove(this);
            }
            else PropertyUtil.setProperty(mView, k, value);
        }
    }
}
