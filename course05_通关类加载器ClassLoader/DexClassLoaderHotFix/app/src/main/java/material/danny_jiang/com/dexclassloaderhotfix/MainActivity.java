package material.danny_jiang.com.dexclassloaderhotfix;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * @author Danny 姜
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    ISay say;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSay = findViewById(R.id.btn_say);
        btnSay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取hotfix的jar包文件
                final File jarFile = new File(Environment.getExternalStorageDirectory().getPath()
                        + File.separator + "say_something_hotfix.jar");

                if (!jarFile.exists()) {
                    say = new SayException();
                    Toast.makeText(MainActivity.this, say.saySomething(), Toast.LENGTH_SHORT).show();
                } else {
                    // 只要有读写权限的路径均可
                    DexClassLoader dexClassLoader = new DexClassLoader(jarFile.getAbsolutePath(),
                            getExternalCacheDir().getAbsolutePath(), null, getClassLoader());
                    try {
                        // 加载 SayHotFix 类
                        Class clazz = dexClassLoader.loadClass("material.danny_jiang.com.dexclassloaderhotfix.SayHotFix");
                        // 强转成 ISay, 注意 ISay 的包名需要和hotfix jar包中的一致
                        ISay iSay = (ISay) clazz.newInstance();
                        Toast.makeText(MainActivity.this, iSay.saySomething(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
