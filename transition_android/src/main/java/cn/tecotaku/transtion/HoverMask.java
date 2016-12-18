package cn.tecotaku.transtion;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Iterator;

/**
 * Created by hakurei on 16-12-18.
 */
public class HoverMask extends View {

    int delta;

    public HoverMask(Context context) {
        super(context);
    }

    public HoverMask(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HoverMask(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void refresh (int delta) {
        this.delta = delta;
        this.postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator<MaskContainer> it = AnimatorManager.maskQueue.iterator();
        while(it.hasNext()){
            MaskContainer vc = it.next();
            vc.refresh(delta, canvas);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return false;
    }
}
