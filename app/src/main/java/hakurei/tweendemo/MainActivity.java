package hakurei.tweendemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v4.animation.ValueAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.tecotaku.transtion.AnimatorManager;
import cn.tecotaku.transtion.ViewContainer;

public class MainActivity extends AppCompatActivity {
    TextView t;
    ViewContainer vd;
    int index = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnimatorManager.registActivity(this);
        t = (TextView) findViewById(R.id.helo);
        vd = new ViewContainer(t);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index += 1;
                if (index %2 ==1){
                    vd.setProperty("backgroundColor", Color.BLACK,3000);
                }else  vd.setProperty("backgroundColor", Color.WHITE,3000);
            }
        });


    }

}
