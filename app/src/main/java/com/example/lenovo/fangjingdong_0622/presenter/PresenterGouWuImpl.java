package com.example.lenovo.fangjingdong_0622.presenter;


import android.util.Log;

import com.example.lenovo.fangjingdong_0622.bean.GouWuBean;
import com.example.lenovo.fangjingdong_0622.http.HttpConfig;
import com.example.lenovo.fangjingdong_0622.model.GouWuCheListener;
import com.example.lenovo.fangjingdong_0622.model.IGouWuModel;
import com.example.lenovo.fangjingdong_0622.model.JiSuanListener;
import com.example.lenovo.fangjingdong_0622.view.gouwuche.IGouWuView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/6/23.
 */

public class PresenterGouWuImpl implements IGouWuPresenter{
    private static final String TAG = "PresenterGouWuImpl-------";
    @Override
    public void showGouWuCheToView(IGouWuModel iGouWuModel, final IGouWuView iGouWuView) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", "71");
        iGouWuModel.getGouWuCheData(HttpConfig.gouwuche_url, map, new GouWuCheListener() {
            @Override
            public void getDataSuccess(String json) {
                Gson gson = new Gson();
                GouWuBean gouWuBean = gson.fromJson(json, GouWuBean.class);
                List<GouWuBean.DataBean> list = gouWuBean.getData();
                iGouWuView.showGowWuList(list);
            }

            @Override
            public void getDataError(String error) {
                Log.d(TAG, "getDataError: "+error);
            }
        });
    }

    @Override
    public void showSumToView(IGouWuModel iGouWuModel, final IGouWuView iGouWuView) {
          iGouWuModel.jisuan(iGouWuView.getList(), new JiSuanListener() {
              @Override
              public void jiSuan(double price) {
                  iGouWuView.showSum(price);
              }
          });
    }
}
