package com.example.lenovo.fangjingdong_0622.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.fangjingdong_0622.R;

/**
 * Created by lenovo on 2018/6/22.
 */

public class SplashActivity extends BaseActivity implements View.OnClickListener{

    private View view;
    private ImageView splash_pic;
    private TextView splash_time;
    private int time=3;
    private TextView splash_tiao;
    private MyHandler myHandler = new MyHandler();

    @Override
    protected void initViews() {

        splash_pic = (ImageView) findViewById(R.id.splash_pic);
        splash_time = (TextView) findViewById(R.id.splash_time);
        splash_tiao = (TextView) findViewById(R.id.splash_tiao);
        splash_tiao.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
       splash_time.setText(time+"s");
        myHandler.sendEmptyMessageDelayed(0,1000);
    }


    @Override
    public void onClick(View v) {

        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    time--;
                    splash_time.setText(time+"s");
                    if(time==0){
                        myHandler.removeCallbacksAndMessages(null);
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        finish();
                    }else{
                        myHandler.sendEmptyMessageDelayed(0,1000);
                    }
                    break;
            }

        }
    }

    @Override
    View createView() {

        view = View.inflate(this, R.layout.layout_spalsh, null);
        return view;
    }
}
