package com.example.day7zuoye2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Bean.DataBean> list=new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }
    public void additem(List<Bean.DataBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getChildren().get(childPosition);
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.itemgroup, null);
        TextView tv = inflate.findViewById(R.id.tvgroup);
        String name = list.get(groupPosition).getName();
        tv.setText(name);


        return inflate;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.itemchildren, null);
        TextView tv = inflate.findViewById(R.id.tvchildren);
        String name = list.get(groupPosition).getChildren().get(childPosition).getName();
        tv.setText(name);



        return inflate;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
