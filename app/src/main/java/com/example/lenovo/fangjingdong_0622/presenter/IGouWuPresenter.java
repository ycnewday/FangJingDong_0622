package com.example.lenovo.fangjingdong_0622.presenter;

import com.example.lenovo.fangjingdong_0622.model.IGouWuModel;
import com.example.lenovo.fangjingdong_0622.view.gouwuche.IGouWuView;

/**
 * Created by lenovo on 2018/6/23.
 */

public interface IGouWuPresenter {
    void showGouWuCheToView(IGouWuModel iGouWuModel, IGouWuView iGouWuView);
  //将计算结果显示到view
          void showSumToView(IGouWuModel iGouWuModel, IGouWuView iGouWuView);
}
