package com.example.lenovo.fangjingdong_0622.view.gouwuche;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.fangjingdong_0622.R;

/**
 * Created by lenovo on 2018/6/23.
 */

public class AddSubView extends LinearLayout implements View.OnClickListener{

    private TextView sub;
    private TextView add;
    private EditText count;

    public AddSubView(Context context) {
        this(context,null);
    }

    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddSubView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.add_layout, this);
       add = (TextView) view.findViewById(R.id.add);
      sub = (TextView)view.findViewById(R.id.sub);
     add.setOnClickListener(this);
     sub.setOnClickListener(this);
     count = (EditText)view.findViewById(R.id.count);
    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.add:
                  add();
                  break;
              case R.id.sub:
                  sub();
              break;
          }
    }

    //添加
    public void add(){
        String s = count.getText().toString();
        double i = Double.parseDouble(s);
        i++;
        count.setText(i+"");
    }
    //减少
    public void sub(){
        String s = count.getText().toString();
        double i = Double.parseDouble(s);
        if(i>=1){
            i--;
            count.setText(i+"");
        }
    }
    //设置数量
   //获取数量
         public String getCount() {
  return count.getText().toString();
  }
 public void setCount(double count1){
  count.setText(count1+"");
  }
}
