package com.example.day2wan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ElvAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<UserBean.DataDTO> list=new ArrayList<>();

    public ElvAdapter(Context context) {
        this.context = context;
    }
    public void getitem(ArrayList<UserBean.DataDTO> list){
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_group, parent, false);
        TextView tv = inflate.findViewById(R.id.tv_item_group);
        String name = list.get(groupPosition).getName();
        tv.setText(name);
        return inflate;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_children, parent, false);
        TextView tv = inflate.findViewById(R.id.tv_item_children);
        String name = list.get(groupPosition).getChildren().get(childPosition).getName();
        tv.setText(name);
        return inflate;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
