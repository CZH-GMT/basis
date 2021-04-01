package com.example.a2;

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

import javax.microedition.khronos.opengles.GL;

class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Bean.DataBean> list = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void additem(List<Bean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
//        ViewHolder viewHolder= (ViewHolder) holder;
//        Bean.DataBean dataBean = list.get(position);
//        Glide.with(context).load(dataBean.getImagePath()).into(viewHolder.iv);
//        viewHolder.tv.setText(dataBean.getTitle());
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onItemClickListener!=null){
//                    onItemClickListener.OnItemClickListener(v,position);
//                }
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    public static
//    class ViewHolder extends RecyclerView.ViewHolder {
//        public View rootView;
//        public ImageView iv;
//        public TextView tv;
//
//        public ViewHolder(View rootView) {
//            super(rootView);
//            this.rootView = rootView;
//            this.iv = (ImageView) rootView.findViewById(R.id.iv);
//            this.tv = (TextView) rootView.findViewById(R.id.tv);
//        }

    }
//    public interface OnItemClickListener{
//        void OnItemClickListener(View v,int position);
//    }
//    private OnItemClickListener onItemClickListener;
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//}
