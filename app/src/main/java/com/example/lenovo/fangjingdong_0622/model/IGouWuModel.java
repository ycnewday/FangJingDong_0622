package com.example.lenovo.fangjingdong_0622.model;

import com.example.lenovo.fangjingdong_0622.bean.GouWuBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/6/23.
 */

public interface IGouWuModel {
    //获取购物车数据
  void getGouWuCheData(String url, Map<String,String> map, GouWuCheListener gouWuCheListener);
   //计算总价
          void jisuan(List<GouWuBean.DataBean> list, JiSuanListener jiSuanListener);
}
