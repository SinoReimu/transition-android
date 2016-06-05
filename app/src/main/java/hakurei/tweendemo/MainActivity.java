package hakurei.tweendemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.tecotaku.transtion.ViewContainer;

public class MainActivity extends AppCompatActivity {
    TextView t;
    ViewContainer vd;
    int index = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = (TextView) findViewById(R.id.helo);

        vd = new ViewContainer(t);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index += 1;
                if (index %2 ==1){
                    vd.setProperty("scaleX",1.9f,3000);
                }else  vd.setProperty("scaleX",0.2f,3000);
            }
        });
    }
}
