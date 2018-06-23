package com.example.lenovo.fangjingdong_0622.view.faxian;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.fangjingdong_0622.R;

/**
 * Created by lenovo on 2018/6/22.
 */

public class FaXianFragment extends Fragment {
    private Context context;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment3, container, false);
        context = view.getContext();
        return view;
    }
}
