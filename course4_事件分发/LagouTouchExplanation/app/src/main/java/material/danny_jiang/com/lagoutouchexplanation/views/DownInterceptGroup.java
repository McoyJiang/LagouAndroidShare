package material.danny_jiang.com.lagoutouchexplanation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import static material.danny_jiang.com.lagoutouchexplanation.PrintUtils.printEvent;

/**
 * Created by Danny 姜.
 */
public class DownInterceptGroup extends FrameLayout {

    private static final String TAG = DownInterceptGroup.class.getSimpleName();

    public DownInterceptGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        printEvent(TAG, "dispatchTouchEvent", event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        printEvent(TAG, "onInterceptTouchEvent", ev);
        return super.onInterceptTouchEvent(ev);
    }

}
