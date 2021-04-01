package com.example.qizhongqianyitain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Bean.DataBean> list =new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;

    }
    public void additems(List<Bean.DataBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void additem(Bean.DataBean dataBean){
        list.add(dataBean);
        notifyDataSetChanged();
    }
    public String getitem(int position ){
        return list.get(position);

    }
    public void updeleteitem(int position){
        list.remove(position);
        notifyDataSetChanged();
    }
    public void undataitem(int position, Bean.DataBean){
        list.set(position,string);
    }
    public int position;

    public MyAdapter(int intposition) {
        this.position = intposition;
    }

    public int getIntposition() {
        return position;
    }

    public void setIntposition(int intposition) {
        this.position = intposition;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        Bean.DataBean dataBean = list.get(position);
        Glide.with(context).load(dataBean.getImagePath()).into(viewHolder.iv);
        viewHolder.tv.setText(dataBean.getDesc());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClikListener!=null){
                    onItemClikListener.OnItemClik(v,position);
                }

            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setIntposition(position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv;
        public TextView tv;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
            this.tv = (TextView) rootView.findViewById(R.id.tv);
        }

    }
    public interface OnItemClikListener{
        void OnItemClik(View v,int position);
    }
    private OnItemClikListener onItemClikListener;

    public void setOnItemClikListener(OnItemClikListener onItemClikListener) {
        this.onItemClikListener = onItemClikListener;
    }
}
