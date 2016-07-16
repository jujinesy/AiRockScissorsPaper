package com.wakira.ai.rockscissorspaper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int AI_Point;

    Button btn1, btn2, btn3;

    @Override
    protected void onStart() {
        //Toast.makeText(getApplicationContext(),"onStart() Call.",Toast.LENGTH_LONG).show();test
        if (init() == 0) {
            AI_Point = 10;
            SavePoint(AI_Point);
        } else {
            AI_Point = LoadPoint();
        }
        TextView textFruit = (TextView) findViewById(R.id.textView3);
        textFruit.setText(String.valueOf(AI_Point));
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TextView)findViewById(R.id.textView3)).setText(String.valueOf(AI_Point));
        ((TextView)findViewById(R.id.textView4)).setText("v"+BuildConfig.VERSION_NAME);

        btn1 = (Button) findViewById(R.id.Button1);
        btn2 = (Button) findViewById(R.id.Button2);
        btn3 = (Button) findViewById(R.id.Button3);
        //생성과, 초기화 버튼 리스너 등록
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == btn1) {
            TextView textFruit = (TextView) findViewById(R.id.textView3);

            if (Integer.parseInt((String) textFruit.getText()) == 0) {
                new AlertDialog.Builder(this)
                        .setTitle("AI 포인트 부족")
                        .setMessage("AI 포인트를 충전해주세요.")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                            // 기본적으로 창은 닫히고 추가 작업은 없다(닫히면서 행해지는 것)
                            }
                        })
                        .show();
            } else {
                ImageView ivBall;
                int v1 = new Random(System.currentTimeMillis()).nextInt(3);
                ivBall = (ImageView) this.findViewById(R.id.ImageView1);
                ivBall.setImageResource(R.drawable.paper + v1);
                textFruit.setText(String.valueOf(--AI_Point));
                SavePoint(AI_Point);
            }
        } else if (v == btn2) {
            this.startActivityForResult(new Intent(((Context)this), BuyActivity.class), getResources().getInteger(R.integer.BuyActivity));
        } else if (v == btn3) {
            System.exit(0);
        }
    }

    private int SavePoint(int point) {
        SharedPreferences pref = getSharedPreferences("AI", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("point", point).commit();
        return point;
    }

    private int LoadPoint() {
        SharedPreferences pref = getSharedPreferences("AI", Activity.MODE_PRIVATE);
        return pref.getInt("point", 0);
        //Toast.makeText(getApplicationContext(),"Load Score : "+score,Toast.LENGTH_LONG).show();
    }

    private int init() {
        SharedPreferences pref = getSharedPreferences("AI", Activity.MODE_PRIVATE);
        if (pref.getInt("init", 0) == 0) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("init", 1).commit();
            return 0;
        }
        return 1;
        //Toast.makeText(getApplicationContext(),"Load Score : "+score,Toast.LENGTH_LONG).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == getResources().getInteger(R.integer.BuyActivity) && resultCode == 200) {
            this.AI_Point += data.getIntExtra("AI", 0);
            TextView textFruit = (TextView) findViewById(R.id.textView3);
            textFruit.setText(String.valueOf(AI_Point));
            SavePoint(AI_Point);
        }
    }

}
