package com.example.day2zao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class ElvAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<UserBean> list = new ArrayList<>();


    public ElvAdapter(Context context) {
        this.context = context;
    }

    public void getitem(ArrayList<UserBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group, parent, false);
        ImageView viewById = view.findViewById(R.id.iv_item_group);
        TextView viewById1 = view.findViewById(R.id.tv_item_group);
        UserBean userBean = list.get(groupPosition);
        viewById.setImageResource(userBean.getImage());
        viewById1.setText(userBean.getName());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_children, parent, false);
        ImageView iv = inflate.findViewById(R.id.iv_item_children);
        TextView tv = inflate.findViewById(R.id.tv_item_children);
        UserBean.personBean personBean = list.get(groupPosition).getList().get(childPosition);
        iv.setImageResource(personBean.getImage());
        tv.setText(personBean.getName());

        return inflate;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;


    }

    public void deleter(int groupItem, int childrenItem) {
        list.get(groupItem).getList().remove(childrenItem);
        notifyDataSetChanged();
    }
}
