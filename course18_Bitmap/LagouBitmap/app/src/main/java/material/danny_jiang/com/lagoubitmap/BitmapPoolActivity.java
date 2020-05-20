package material.danny_jiang.com.lagoubitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃ 神兽保佑
 * 　　　　┃　　　┃ 代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * <p>
 * Created by Danny 姜.
 */
public class BitmapPoolActivity extends AppCompatActivity {

    private static final String TAG = BitmapPoolActivity.class.getSimpleName();

    ImageView poolImage;
    Bitmap reuseBitmap;
    int resIndex;
    int[] resIds = {R.drawable.rodman, R.drawable.rodman2};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool);
        poolImage = findViewById(R.id.poolImage);
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        reuseBitmap = BitmapFactory.decodeResource(getResources(), resIds[0], options);
    }

    public void switchImage(View view) {
        poolImage.setImageBitmap(getBitmap());
    }

    private Bitmap getBitmap() {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resIds[resIndex % 2], options);
        if (canUseForInBitmap(reuseBitmap, options)) {
            Log.e(TAG, "reuseBitmap is reusable");
            options.inMutable = true;
            options.inBitmap = reuseBitmap;
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), resIds[resIndex++ % 2], options);
    }

    public static boolean canUseForInBitmap(Bitmap candidate, BitmapFactory.Options targetOptions) {
        int width = targetOptions.outWidth / Math.max(targetOptions.inSampleSize, 1);
        int height = targetOptions.outHeight / Math.max(targetOptions.inSampleSize, 1);
        int byteCount = width * height * getBytesPerPixel(candidate.getConfig());

        return byteCount <= candidate.getAllocationByteCount();
    }

    private static int getBytesPerPixel(Bitmap.Config config) {
        int bytesPerPixel;
        switch (config) {
            case ALPHA_8:
                bytesPerPixel = 1;
                break;
            case RGB_565:
            case ARGB_4444:
                bytesPerPixel = 2;
                break;
            default:
                bytesPerPixel = 4;
                break;
        }
        return bytesPerPixel;
    }
}
