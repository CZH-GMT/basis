package com.example.a4;

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

class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    public List<Beans.DataBean.DatasBean> list = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void additem(List<Beans.DataBean.DatasBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rcy_item, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        final Beans.DataBean.DatasBean datasBean = list.get(position);
        Glide.with(context).load(datasBean.getEnvelopePic()).into(viewHolder.iv);
        viewHolder.title.setText(datasBean.getTitle());
        viewHolder.desc.setText(datasBean.getDesc());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onitemclicklistener!=null){
                    onitemclicklistener.onitemclicklistener(datasBean);
                }
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
    public interface onitemclicklistener{
        void onitemclicklistener(Beans.DataBean.DatasBean bean);
    }
    private onitemclicklistener onitemclicklistener;

    public void setOnitemclicklistener(MyAdapter.onitemclicklistener onitemclicklistener) {
        this.onitemclicklistener = onitemclicklistener;
    }
}

