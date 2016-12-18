package cn.tecotaku.transtion;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import cn.tecotaku.transtion.utils.PropertyUtil;

/**
 * Created by hakurei on 16-12-18.
 */
public class MaskContainer {

    public float startOffset;
    public float endOffset;
    public float currentOffset;
    public int duration;
    public int hasUse;
    public int color;
    public int maxR;
    public View view;
    private Paint paint;
    private float x,y;

    public MaskContainer(View view, float endOffset, float startOffset, int maxR, int du, int color){
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.duration = du;
        this.maxR = maxR;
        this.color = color;
        this.hasUse = 0;
        this.view = view;
        x = view.getX();
        y = view.getY();
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(color);
    }

    /*
     * function: refresh
     *
     * params: delta:int --- how many time after last refresh
     * return value: -1 means animation finished else means Current Animated Value
     */

    public void refresh(int delta, Canvas canvas){
        hasUse += delta;
        if (hasUse >= duration) AnimatorManager.maskQueue.remove(this);
        float timeOffset = 1.0f*hasUse/duration;
        currentOffset = PropertyUtil.calculate(startOffset , endOffset, timeOffset);
        paint.setAlpha((int)(255*(1-currentOffset)));
        canvas.drawCircle(x, y, currentOffset*maxR, paint);
    }
}
