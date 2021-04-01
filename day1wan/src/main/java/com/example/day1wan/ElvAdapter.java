package com.example.day1wan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ElvAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<UserBean.DataDTO> data=new ArrayList<>();

    public ElvAdapter(Context context) {
        this.context = context;
    }
    public void getitem(List<UserBean.DataDTO> data){

        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        UserBean.DataDTO dataDTO = data.get(groupPosition);
        int size = dataDTO.getChildren().size();
        return data.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getChildren().get(childPosition);
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
        TextView viewById = view.findViewById(R.id.tv_item_group);
        String errorMsg = data.get(groupPosition).getName();
        viewById.setText(errorMsg);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_children, parent, false);
        ImageView iv = inflate.findViewById(R.id.iv_item_children);
        TextView tv = inflate.findViewById(R.id.tv_item_children);
        String name = data.get(groupPosition).getChildren().get(childPosition).getName();


        tv.setText(name);
        return inflate;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
