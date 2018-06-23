package com.example.lenovo.fangjingdong_0622.view.gouwuche;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.fangjingdong_0622.R;
import com.example.lenovo.fangjingdong_0622.adapters.MyAdapter;
import com.example.lenovo.fangjingdong_0622.bean.GouWuBean;
import com.example.lenovo.fangjingdong_0622.model.GouWuModelImpl;
import com.example.lenovo.fangjingdong_0622.presenter.PresenterGouWuImpl;

import java.util.List;

/**
 * Created by lenovo on 2018/6/22.
 */

public class GouWuFragment extends Fragment implements View.OnClickListener, IGouWuView {


    private ExpandableListView expandableListView;
    private CheckBox checkBox;
    private TextView tv_sum;
    private Button jisuan;
    private static final String TAG = "MainActivity0------";
    private List<GouWuBean.DataBean> list_tmp;
    private MyAdapter myAdapter;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment4, container, false);
        context = view.getContext();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化控件
        initViews();
        //请求数据
        initDatas();
    }

    private void initDatas() {
        PresenterGouWuImpl presenter = new PresenterGouWuImpl();
        presenter.showGouWuCheToView(new GouWuModelImpl(), this);

    }

    private void initViews() {
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandable_list_view);
        checkBox = (CheckBox) view.findViewById(R.id.cb);
        tv_sum = (TextView) view.findViewById(R.id.tv_sum);
        jisuan = (Button) view.findViewById(R.id.jisuan);
        checkBox.setOnClickListener(this);
        jisuan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //全选，全部选  总价
            case R.id.cb:
                boolean checked = checkBox.isChecked();
                for (int i = 0; i < list_tmp.size(); i++) {
                    list_tmp.get(i).setParent_flag(checked);
                    List<GouWuBean.DataBean.ListBean> list_child = list_tmp.get(i).getList();
                    for (int j = 0; j < list_child.size(); j++) {
                        list_child.get(j).setChild_flag(checked);
                    }
                }
                //刷新适配器
                myAdapter.notifyDataSetChanged();
                //调用计算总价的方法
                PresenterGouWuImpl presenter = new PresenterGouWuImpl();
                presenter.showSumToView(new GouWuModelImpl(), this);
                break;
            case R.id.jisuan:
                Toast.makeText(context, "去计算总价", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void showGowWuList(List<GouWuBean.DataBean> list) {
        list_tmp = list;
        Log.d(TAG, "showGowWuList: " + list);
        myAdapter = new MyAdapter(context, list, this);
        expandableListView.setAdapter(myAdapter);
        //展开expandablelistview
        int childCount = expandableListView.getCount();
        for (int i = 0; i < childCount; i++) {
            expandableListView.expandGroup(i);
        }
    }

    @Override
    public void showSum(double price) {
        tv_sum.setText("合计:" + price);
    }

    @Override
    public List<GouWuBean.DataBean> getList() {
        return list_tmp;
    }
}
