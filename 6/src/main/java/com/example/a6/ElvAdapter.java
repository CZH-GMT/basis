package com.example.a6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ElvAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<ElvBean.DataBean> list=new ArrayList<>();

    public ElvAdapter(Context context) {
        this.context = context;
    }

    public void  addiiitem(List<ElvBean.DataBean> list){
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.itemgroup, parent, false);
        TextView tv = inflate.findViewById(R.id.tvgroup);
        tv.setText(list.get(groupPosition).getName());
        return inflate;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.itemchildren, parent, false);
        TextView tvchild = inflate.findViewById(R.id.tvchildren);
        tvchild.setText(list.get(groupPosition).getChildren().get(childPosition).getName());

        return inflate;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
