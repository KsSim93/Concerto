package com.example.aircleaner;

/**
 * Created by 희연 on 2017-05-28.
 */

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ch_rgb extends Activity {
    int r, g, b;
    EditText rr, gg, bb;
    TextView textview;
    String r1, r2, r3;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rgb);


        rr = (EditText) findViewById(R.id.red);
        gg = (EditText) findViewById(R.id.green);
        bb = (EditText) findViewById(R.id.blue);
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                r1 = r2 = r3 = "";
                r1 = rr.getText().toString();
                r2 = gg.getText().toString();
                r3 = bb.getText().toString();
                r = Integer.parseInt(r1);
                g = Integer.parseInt(r2);
                b = Integer.parseInt(r3);
                if (0 <= r && r <= 255 && g >= 0 && g <= 255 && b >= 0 && b <= 255) {
                    textview.setTextColor(Color.rgb(r, g, b));
                    Toast.makeText(getApplicationContext(), "변환완료", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "0~255 사이의 값을 입력해주세요", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
