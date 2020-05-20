package material.danny_jiang.com.lagoubitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Bitmap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.image);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inPreferredConfig = Bitmap.Config.RGB_565;
//        options.inSampleSize = 2;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rodman, options);
        Log.i(TAG, "bitmap size is " + bitmap.getAllocationByteCount());

        int realBitmapSize = getRealBitmapCount(600, 600);
        Log.i(TAG, "realBitmapSize size is " + realBitmapSize);
        imageView.setImageBitmap(bitmap);
    }

    private int getRealBitmapCount(int bitmapWidth, int bitmapHeight) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float scale = displayMetrics.densityDpi / 320f;
        return (int) (bitmapWidth * scale * bitmapHeight * scale * 4);
    }
}
