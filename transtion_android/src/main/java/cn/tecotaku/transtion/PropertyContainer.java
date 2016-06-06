package cn.tecotaku.transtion;

import android.util.Log;

import cn.tecotaku.transtion.interpolator.Interpolator;
import cn.tecotaku.transtion.utils.PropertyUtil;

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
    public Interpolator interpolator;

    public PropertyContainer(float start, float end, int du, Interpolator interpolator){
        startValue = start;
        endValue = end;
        duration = du;
        hasUse = 0;
        this.interpolator = interpolator;
    }

    /*
     * function: refresh
     *
     * params: delta:int --- how many time after last refresh
     * return value: -1 means animation finished else means Current Animated Value
     */

    public float refresh(String key, int delta){
        hasUse += delta;
        float offset = interpolator.interpolate(1.0f * hasUse / duration);
        if (hasUse >= duration) return -1;
        if(key.equals("backgroundColor")){
            currentValue = PropertyUtil.calculateColor(startValue ,endValue, offset);
        }else {

            //Log.i("animator", "du:"+duration+"  haspass:"+hasUse+"start:"+startValue+" current:"+currentValue+" end:"+endValue);
            currentValue = PropertyUtil.calculate(startValue ,endValue,offset);
        }
        return currentValue;
    }



}
