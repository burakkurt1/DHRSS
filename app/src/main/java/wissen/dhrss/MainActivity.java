package wissen.dhrss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.imageView);
        Animation anm = AnimationUtils.loadAnimation(this, R.anim.logo_animasyon);
        iv.startAnimation(anm);

        new Thread()
        {
            @Override
            public void run() {
                try {Thread.sleep(2000); } catch (Exception e) { }
                startActivity(new Intent(MainActivity.this, HaberListesi.class));
                finish();
            }
        }.start();
    }
}
