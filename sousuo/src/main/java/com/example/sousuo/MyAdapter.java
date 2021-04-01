package com.example.sousuo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Bean.DataBean.DatasBean> list = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void addtem(List<Bean.DataBean.DatasBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Bean.DataBean.DatasBean datasBean = list.get(position);
        ViewHolder viewHolder= (ViewHolder) holder;
        viewHolder.tv.setText(datasBean.getTitle());
        viewHolder.author.setText(datasBean.getAuthor());
        viewHolder.time.setText(datasBean.getNiceDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv;
        public TextView author;
        public TextView time;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv = (TextView) rootView.findViewById(R.id.tv);
            this.author = (TextView) rootView.findViewById(R.id.author);
            this.time = (TextView) rootView.findViewById(R.id.time);
        }

    }
}
