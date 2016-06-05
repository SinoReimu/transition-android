package cn.tecotaku.transtion.utils;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by Administrator on 2016/6/5 0005.
 */
public class PropertyUtil {

    /*
     * function: getProperty
     * param: v:View --- target View
     *        key:String --- which property needed in this View
     *
     * return: value:double --- target value
     */

    public static float getProperty(View v, String key){
        float value = 0;
        if (key.equals("alpha")) value = ViewHelper.getAlpha(v);
        else if (key.equals("scaleX")) value = ViewHelper.getScaleX(v);
        else if (key.equals("scaleY")) value = ViewHelper.getScaleY(v);
        else if (key.equals("rotationX")) value = ViewHelper.getRotationX(v);
        else if (key.equals("rotationY")) value = ViewHelper.getRotationY(v);
        else if (key.equals("translateX")) value = ViewHelper.getTranslationX(v);
        else if (key.equals("translateY")) value = ViewHelper.getTranslationY(v);
        else if (key.equals("X")) value = ViewHelper.getX(v);
        else if (key.equals("Y")) value = ViewHelper.getY(v);
        return value;
    }

    /*
     * function: setProperty
     * param: v:View --- target View
     *        key:String --- which property needed in this View
     *        value:double --- how many to set
     *
     * return: null
     */

    public static void setProperty(View v, String key, float value){
        if(v==null) return;
        else{
            if (key.equals("alpha")) ViewHelper.setAlpha(v, value);
            else if (key.equals("scaleX")) ViewHelper.setScaleX(v, value);
            else if (key.equals("scaleY")) ViewHelper.setScaleY(v, value);
            else if (key.equals("rotationX")) ViewHelper.setRotationX(v, value);
            else if (key.equals("rotationY")) ViewHelper.setRotationY(v, value);
            else if (key.equals("translateX")) ViewHelper.setTranslationX(v, value);
            else if (key.equals("translateY")) ViewHelper.setTranslationY(v, value);
            else if (key.equals("X")) ViewHelper.setX(v, value);
            else if (key.equals("Y"))ViewHelper.setY(v, value);
        }
    }

}
