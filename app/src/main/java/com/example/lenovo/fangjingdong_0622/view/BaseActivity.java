package com.example.lenovo.fangjingdong_0622.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

/**
 * Created by lenovo on 2018/6/22.
 */

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(createView());
        //初始化界面
        initViews();
        //初始化数据
        initDatas();
    }
    protected abstract void initViews();

    protected abstract void initDatas();

    abstract View createView();
}
