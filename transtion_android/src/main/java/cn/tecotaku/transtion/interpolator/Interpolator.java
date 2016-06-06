package cn.tecotaku.transtion.interpolator;

import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by HakureiSino on 2016/6/6 0006.
 */

public abstract class Interpolator {
    public abstract float interpolate (float offset);
}
