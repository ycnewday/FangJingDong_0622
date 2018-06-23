package com.example.lenovo.fangjingdong_0622.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.fangjingdong_0622.R;
import com.example.lenovo.fangjingdong_0622.bean.GouWuBean;
import com.example.lenovo.fangjingdong_0622.model.GouWuModelImpl;
import com.example.lenovo.fangjingdong_0622.presenter.PresenterGouWuImpl;
import com.example.lenovo.fangjingdong_0622.view.gouwuche.AddSubView;
import com.example.lenovo.fangjingdong_0622.view.gouwuche.IGouWuView;

import java.util.List;

/**
 * Created by lenovo on 2018/6/23.
 */

public class MyAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<GouWuBean.DataBean> list;
    private IGouWuView iGouWuView;

    public MyAdapter(Context context, List<GouWuBean.DataBean> list, IGouWuView iGouWuView) {
        this.context = context;
        this.list = list;
        this.iGouWuView = iGouWuView;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder parentViewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_parent, null);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.parent_cb);
            TextView textView = (TextView) convertView.findViewById(R.id.parent_title);
            parentViewHolder = new ParentViewHolder(checkBox, textView);
            convertView.setTag(parentViewHolder);
        } else {
            parentViewHolder = (ParentViewHolder) convertView.getTag();
        }
        //赋值
        parentViewHolder.getParent_tv().setText(list.get(groupPosition).getSellerName());
        parentViewHolder.getParent_cb().setChecked(list.get(groupPosition).isParent_flag());
        //单选框的点击事件
        final ParentViewHolder finalParentViewHolder = parentViewHolder;
        //点击事件
        parentViewHolder.getParent_cb().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = finalParentViewHolder.getParent_cb().isChecked();
                //改变bean里面的值
                list.get(groupPosition).setParent_flag(isChecked);
                //给子列表赋值
                List<GouWuBean.DataBean.ListBean> list_child = MyAdapter.this.list.get(groupPosition).getList();
                for (int i = 0; i < list_child.size(); i++) {
                    GouWuBean.DataBean.ListBean childBean = list_child.get(i);
                    //改变bean里面的值
                    childBean.setChild_flag(isChecked);
                }
                notifyDataSetChanged();
                //调用计算的方法
                PresenterGouWuImpl presenterGouWu = new PresenterGouWuImpl();
                presenterGouWu.showSumToView(new GouWuModelImpl(), iGouWuView);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_child, null);
            CheckBox child_cb = (CheckBox) convertView.findViewById(R.id.child_cb);
            TextView child_title = (TextView) convertView.findViewById(R.id.child_title);
            TextView dele = (TextView) convertView.findViewById(R.id.dele);
            ImageView child_pic = (ImageView) convertView.findViewById(R.id.child_pic);
            TextView tv_price = (TextView) convertView.findViewById(R.id.child_price);
            AddSubView addSubView = (AddSubView) convertView.findViewById(R.id.addSubView);
            childViewHolder = new ChildViewHolder(child_cb, child_pic, child_title, dele, tv_price, addSubView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        //赋值
        final List<GouWuBean.DataBean.ListBean> child_list = this.list.get(groupPosition).getList();
        childViewHolder.getChild_title().setText(child_list.get(childPosition).getTitle());
        childViewHolder.getChild_price().setText(child_list.get(childPosition).getPrice() + "");
        //给单选框赋值
        childViewHolder.getChild_cb().setChecked(child_list.get(childPosition).isChild_flag());
        String images = child_list.get(childPosition).getImages();
        String pic_url = images.split("\\|")[0];
        Glide.with(context).load(pic_url).into(childViewHolder.getChild_pic());
        childViewHolder.getAddSubView().setCount(child_list.get(childPosition).getNum());
        //字条目单选框的点击事件
        final ChildViewHolder finalChildViewHolder = childViewHolder;
        childViewHolder.getChild_cb().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取当前条目状态
                boolean checked = finalChildViewHolder.getChild_cb().isChecked();
                //赋值--改变bean
                child_list.get(childPosition).setChild_flag(checked);
                //for循环，
                boolean flag = true;
                for (int i = 0; i < child_list.size(); i++) {
                    boolean child_flag = child_list.get(i).isChild_flag();
                    if (child_flag == false) {
                        flag = false;
                    }
                }
                //然后将flag赋值给父条目的bean
                list.get(groupPosition).setParent_flag(flag);
                notifyDataSetChanged();
                //调用计算的方法
                PresenterGouWuImpl presenterGouWu = new PresenterGouWuImpl();
                presenterGouWu.showSumToView(new GouWuModelImpl(), iGouWuView);
            }
        });
        //删除
        childViewHolder.getDelte().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                child_list.remove(childPosition);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    //父布局的ViewHolder
    public class ParentViewHolder {
        private CheckBox parent_cb;
        private TextView parent_tv;

        public ParentViewHolder(CheckBox parent_cb, TextView parent_tv) {
            this.parent_cb = parent_cb;
            this.parent_tv = parent_tv;
        }

        public CheckBox getParent_cb() {
            return parent_cb;
        }

        public void setParent_cb(CheckBox parent_cb) {
            this.parent_cb = parent_cb;
        }

        public TextView getParent_tv() {
            return parent_tv;
        }

        public void setParent_tv(TextView parent_tv) {
            this.parent_tv = parent_tv;
        }
    }

    //子布局的ViewHolder
    public class ChildViewHolder {
        private CheckBox child_cb;
        private ImageView child_pic;
        private TextView child_title;
        private TextView delte;
        private TextView child_price;
        private AddSubView addSubView;

        public ChildViewHolder(CheckBox child_cb, ImageView child_pic, TextView child_title, TextView delte, TextView child_price, AddSubView addSubView) {
            this.child_cb = child_cb;
            this.child_pic = child_pic;
            this.child_title = child_title;
            this.delte = delte;
            this.child_price = child_price;
            this.addSubView = addSubView;
        }

        public CheckBox getChild_cb() {
            return child_cb;
        }

        public void setChild_cb(CheckBox child_cb) {
            this.child_cb = child_cb;
        }

        public ImageView getChild_pic() {
            return child_pic;
        }

        public void setChild_pic(ImageView child_pic) {
            this.child_pic = child_pic;
        }

        public TextView getChild_title() {
            return child_title;
        }

        public void setChild_title(TextView child_title) {
            this.child_title = child_title;
        }

        public TextView getDelte() {
            return delte;
        }

        public void setDelte(TextView delte) {
            this.delte = delte;
        }

        public TextView getChild_price() {
            return child_price;
        }

        public void setChild_price(TextView child_price) {
            this.child_price = child_price;
        }

        public AddSubView getAddSubView() {
            return addSubView;
        }

        public void setAddSubView(AddSubView addSubView) {
            this.addSubView = addSubView;
        }
    }
}
