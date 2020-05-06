package material.danny_jiang.com.lagoutouchexplanation;

import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Danny 姜.
 */
public class PrintUtils {

    public static void printEvent(String tag, String methodName, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(tag, methodName + ":" + "DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(tag, methodName + ":" + "MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(tag, methodName + ":" + "UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(tag, methodName + ":" + "CANCEL");
                break;
        }
    }

    public static void printParam(String tag, String reg) {
        Log.i(tag, reg);
    }
}
