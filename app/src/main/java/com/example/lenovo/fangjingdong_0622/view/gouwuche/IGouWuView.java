package com.example.lenovo.fangjingdong_0622.view.gouwuche;

import com.example.lenovo.fangjingdong_0622.bean.GouWuBean;

import java.util.List;

/**
 * Created by lenovo on 2018/6/23.
 */

public interface IGouWuView {
    //显示数据
  void showGowWuList(List<GouWuBean.DataBean> list);
  //显示总价
          void showSum(double price);
  //获取集合数据
          List<GouWuBean.DataBean> getList();
}
