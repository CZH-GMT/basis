package com.example.day5wan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Bean.DataBean> list ;

    public MyAdapter(Context context, List<Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    //    public void additem(List<Bean.DataBean> list){
//        this.list.addAll(list);
//        notifyDataSetChanged();
//
//    }
    public int position;

    public MyAdapter(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

//    public Bean.DataBean getData(int position){
//        return list.get(position);
//    }

//    public void update(int position, String m, String b){
//        Bean.DataBean dataBean = list.get(position);
//        dataBean.setTitle(m);
//        dataBean.setDesc(b);
//        notifyDataSetChanged();
//    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        Bean.DataBean dataBean = list.get(position);
        Glide.with(context).load(dataBean.getImagePath()).into(viewHolder.iv);
        viewHolder.title.setText(dataBean.getTitle());
        viewHolder.desc.setText(dataBean.getDesc());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnClickListener(v,position);
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(position);

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
        public TextView title;
        public TextView desc;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
            this.title = (TextView) rootView.findViewById(R.id.title);
            this.desc = (TextView) rootView.findViewById(R.id.desc);
        }

    }
    public interface OnItemClickListener{
        void OnClickListener(View v,int position);


    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public void delete(int position){
        list.remove(position);
        notifyDataSetChanged();
    }
}
