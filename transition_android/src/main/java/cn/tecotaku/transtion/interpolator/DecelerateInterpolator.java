package cn.tecotaku.transtion.interpolator;

/**
 * Created by HakureiSino on 2016/6/6.
 */
public class DecelerateInterpolator extends Interpolator {
    @Override
    public float interpolate(float offset) {
        return (float)Math.sqrt(offset);
    }
}
