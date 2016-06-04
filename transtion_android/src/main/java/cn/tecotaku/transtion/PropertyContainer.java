package cn.tecotaku.transtion;

/**
 * Created by HakureiSino on 2016/6/4 0004.
 * 封装属性以及属性变化时间
 */

public class PropertyContainer {

    public double startValue;
    public double endValue;
    public double currentValue;
    public int duration;
    public int hasUse;

    public PropertyContainer(double start, double end, int du){
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

    public double refresh(int delta){
            hasUse += 1;
            currentValue = startValue + (endValue - startValue) * (hasUse/duration);
            if(hasUse == duration) return -1;
            return currentValue;
    }
}
