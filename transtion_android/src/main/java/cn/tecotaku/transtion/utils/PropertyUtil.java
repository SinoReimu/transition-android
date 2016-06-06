package cn.tecotaku.transtion.utils;

import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import cn.tecotaku.transtion.AnimatorManager;

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
            if (key.equals("alpha")) v.setAlpha(value);
            else if (key.equals("scaleX")) v.setScaleX(value);
            else if (key.equals("scaleY")) v.setScaleY(value);
            else if (key.equals("rotationX")) v.setRotationX(value);
            else if (key.equals("rotationY")) v.setRotationY(value);
            else if (key.equals("translateX")) v.setTranslationX(value);
            else if (key.equals("translateY")) v.setTranslationY(value);
            else if (key.equals("X")) v.setX(value);
            else if (key.equals("Y"))v.setY(value);
        }
    }

}
