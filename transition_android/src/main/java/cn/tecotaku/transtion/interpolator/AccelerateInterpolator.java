package cn.tecotaku.transtion.interpolator;

/**
 * Created by HakureiSino on 2016/6/6.
 */
public class AccelerateInterpolator extends Interpolator {
    @Override
    public float interpolate(float offset) {
        return offset * offset;
    }
}
