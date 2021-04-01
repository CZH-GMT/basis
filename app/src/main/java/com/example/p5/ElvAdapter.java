package com.example.p5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class ElvAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<UserBean> list=new ArrayList<>();

    public ElvAdapter(Context context) {
        this.context = context;
    }
    public void addItems(ArrayList<UserBean> list){
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_group, parent, false);
        TextView viewById = inflate.findViewById(R.id.item_group_name);
        String name = list.get(groupPosition).getName();
        viewById.setText(name);
        return inflate;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_children, parent, false);
        ImageView viewById = inflate.findViewById(R.id.iv_item_children);
        TextView viewById1 = inflate.findViewById(R.id.tv_item_children);
        UserBean.PersonBean personBean = list.get(groupPosition).getList().get(childPosition);

        viewById1.setText(personBean.getName());
        viewById.setImageResource(personBean.getImage());
        return inflate;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
