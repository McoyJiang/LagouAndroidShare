package material.danny_jiang.com.lagoucustomizedview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import material.danny_jiang.com.lagoucustomizedview.views.PieImageView;

/**
 * Created by Danny 姜.
 */
public class PieImageActivity extends AppCompatActivity {

    PieImageView pieImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_image);

//        pieImageView = findViewById(R.id.pieImageView);
//        pieImageView.setProgress(45);
    }
}
