package com.example.lenovo.fangjingdong_0622.model;

import android.util.Log;

import com.example.lenovo.fangjingdong_0622.bean.GouWuBean;
import com.example.lenovo.fangjingdong_0622.http.HttpUtils;
import com.example.lenovo.fangjingdong_0622.http.OkLoadListener;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/6/23.
 */

public class GouWuModelImpl implements IGouWuModel {
    private static final String TAG = "GouWuModelImpl-----";

    @Override
    public void getGouWuCheData(String url, Map<String, String> map, final GouWuCheListener gouWuCheListener) {
        HttpUtils httpUtils = HttpUtils.getHttpUtils();
        httpUtils.okPost(url, map);
        httpUtils.setOkLoadListener(new OkLoadListener() {
            @Override
            public void okLoadSuccess(String json) {
                Log.d(TAG, "okLoadSuccess: " + json);
                gouWuCheListener.getDataSuccess(json);
            }

            @Override
            public void okLoadError(String error) {
                Log.d(TAG, "okLoadError: " + error);
                gouWuCheListener.getDataError(error);
            }
        });

    }

    @Override
    public void jisuan(List<GouWuBean.DataBean> list, JiSuanListener jiSuanListener) {

        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            List<GouWuBean.DataBean.ListBean> list_child = list.get(i).getList();
            for (int j = 0; j < list_child.size(); j++) {
                boolean child_flag = list_child.get(j).isChild_flag();
                if (child_flag) {
                    double price = list_child.get(j).getPrice();
                    sum += price;
                }
            }
        }
    }

}