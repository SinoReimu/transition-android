package cn.tecotaku.transtion;

import android.util.Log;

/**
 * Created by HakureiSino on 2016/6/4 0004.
 * 封装属性以及属性变化时间
 */

public class PropertyContainer {

    public float startValue;
    public float endValue;
    public float currentValue;
    public int duration;
    public int hasUse;

    public PropertyContainer(float start, float end, int du){
        startValue = start;
        endValue = end;
        duration = du;
        hasUse = 0;
    }

    /*
     * function: refresh
     *
     * params: delta:int --- how many time after last refresh
     * return value: -1 means animation finished else means Current Animated Value
     */

    public float refresh(int delta){
            hasUse += delta;
            //Log.i("animator", "du:"+duration+"  haspass:"+hasUse+"start:"+startValue+" current:"+currentValue+" end:"+endValue);
            currentValue = startValue + (endValue - startValue) * (1.0f*hasUse/duration);
            if(hasUse >= duration) return -1;
            return currentValue;
    }
}
