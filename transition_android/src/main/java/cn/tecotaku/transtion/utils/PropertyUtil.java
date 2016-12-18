package cn.tecotaku.transtion.utils;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.util.Log;
import android.view.View;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.view.ViewHelper;
import cn.tecotaku.transtion.AnimatorManager;

/**
 * Created by HakureiSino on 2016/6/5 0005.
 * 属性操作封装工具
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
        else if (key.equals("backgroundColor")) {
            Drawable dw =  v.getBackground();
            if (dw instanceof ColorDrawable){
                value = ((ColorDrawable)dw).getColor();
            }else if (dw instanceof BitmapDrawable){
                Log.e("ERROR", "The background of this View is Bitmap so the transition will start from #000000");
                value = Color.BLACK;
            }
        }
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
        if(v==null||!AnimatorManager.isRegistActivityFront) return;
        else{
            if (key.equals("alpha")) ViewHelper.setAlpha(v, value);
            else if (key.equals("scaleX")) ViewHelper.setScaleX(v,value);
            else if (key.equals("scaleY")) ViewHelper.setScaleY(v, value);
            else if (key.equals("rotationX")) ViewHelper.setRotationX(v, value);
            else if (key.equals("rotationY")) ViewHelper.setRotationY(v, value);
            else if (key.equals("translateX")) ViewHelper.setTranslationX(v, value);
            else if (key.equals("translateY")) ViewHelper.setTranslationY(v, value);
            else if (key.equals("X")) ViewHelper.setX(v, value);
            else if (key.equals("Y")) ViewHelper.setY(v, value);
            else if (key.equals("backgroundColor")) {
                Message msg = new Message();
                msg.what = 0x001;msg.obj = v;msg.arg1 = (int)value;
                AnimatorManager.handler.sendMessage(msg);
            }
        }
    }


    public static float calculate (float startValue, float endValue, float offset){
        return startValue + (endValue - startValue) * offset;
    }

    public static ArgbEvaluator evaluator;
    public static float calculateColor (float startValue, float endValue, float offset){
        if (evaluator == null) evaluator = new ArgbEvaluator();
        return (int)evaluator.evaluate(offset, (int)startValue, (int)endValue);
    }
}
