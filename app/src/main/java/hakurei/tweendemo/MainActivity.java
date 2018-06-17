package hakurei.tweendemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cn.tecotaku.transtion.AnimatorManager;
import cn.tecotaku.transtion.ViewContainer;

public class MainActivity extends AppCompatActivity {
    TextView t, t2;
    ViewContainer vd, vd2;
    int index = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnimatorManager.registActivity(this);
        t = (TextView) findViewById(R.id.helo);
        t2 = (TextView) findViewById(R.id.helo2);
        t.setTextColor(Color.WHITE);
        vd = new ViewContainer(t);
        vd2 = new ViewContainer(t2);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index += 1;
                if (index %2 ==1){
                    vd.setProperty("backgroundColor", Color.BLACK,3000);
                    vd.setProperty("alpha", 0.8f,2000);
                    vd.setProperty("scaleX", 0.8f,2000);
                    vd.setProperty("translateX", 200f,800);

                    vd2.setProperty("X", index*50, 1000);
                }else {
                    vd.setProperty("backgroundColor", Color.GREEN, 3000);
                    vd.setProperty("alpha", 0.1f,1000);
                    vd.setProperty("scaleX", 1.8f,8000);
                    vd.setProperty("X", 200f,800);
                    vd.setProperty("translateY", 200f,800);
                }
            }
        });


    }

}
