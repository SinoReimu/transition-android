package cn.tecotaku.transtion.utils;

import android.util.Log;

/**
 * Created by HakureiSino on 2016/6/6 0006.
 * Log工具
 */
public class LogUtil {

    private static int LogLevel = 8;

    private final static int VERBOSE_LEVEL = 1;
    private final static int DEBUG_LEVEL = 2;
    private final static int INFO_LEVEL = 3;
    private final static int WARN_LEVEL = 4;
    private final static int ERROR_LEVEL = 5;

    private final static String TAG = "Animator";

    public static void v (String content){
        if (LogLevel>=VERBOSE_LEVEL) Log.v(TAG, content);
    }

    public static void d (String content){
       if (LogLevel>=DEBUG_LEVEL) Log.d(TAG, content);
    }

    public static void i (String content){
        if (LogLevel>=INFO_LEVEL) Log.i(TAG, content);
    }

    public static void w (String content){
        if (LogLevel>=WARN_LEVEL) Log.w(TAG, content);
    }

    public static void e (String content){
        if (LogLevel>=ERROR_LEVEL) Log.e(TAG, content);
    }

}
