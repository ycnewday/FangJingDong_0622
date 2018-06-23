package com.example.lenovo.fangjingdong_0622.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.lenovo.fangjingdong_0622.R;
import com.example.lenovo.fangjingdong_0622.view.faxian.FaXianFragment;
import com.example.lenovo.fangjingdong_0622.view.feilei.FenLeiFragment;
import com.example.lenovo.fangjingdong_0622.view.gouwuche.GouWuFragment;
import com.example.lenovo.fangjingdong_0622.view.shouye.ShouFragment;
import com.example.lenovo.fangjingdong_0622.view.wode.WoDeFragment;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);

        //初始化Fragment
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(50, 50)      //图片大小
                .setFontSize(12)                       //字体大小
                .setTabPadding(4, 6, 10)//选项卡的间距
                .setChangeColor(Color.RED, Color.BLUE)     //选项卡的选择颜色
                .addTabItem("首页", R.mipmap.ac0, ShouFragment.class)
                .addTabItem("分类", R.mipmap.abw, FenLeiFragment.class)
                .addTabItem("发现", R.mipmap.aby, FaXianFragment.class)
                .addTabItem("购物车", R.mipmap.abu, GouWuFragment.class)
                .addTabItem("我的", R.mipmap.ac2, WoDeFragment.class)
                .isShowDivider(true)    //是否包含分割线
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                        Log.i("TGA", "位置：" + position + "      选项卡：" + name);
                    }
                });

    }
}
