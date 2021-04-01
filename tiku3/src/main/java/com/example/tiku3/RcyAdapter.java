package com.example.tiku3;

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

public class RcyAdapter extends RecyclerView.Adapter {
    private Context context;
    public List<RcyBean.DataBean.DatasBean> list = new ArrayList<>();

    public RcyAdapter(Context context) {
        this.context = context;
    }

    public void addRcyitem(List<RcyBean.DataBean.DatasBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
public int position;

    public RcyAdapter(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rcy_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        final RcyBean.DataBean.DatasBean datasBean = list.get(position);
        Glide.with(context).load(datasBean.getEnvelopePic()).into(viewHolder.iv);
        viewHolder.title.setText(datasBean.getDesc());
        viewHolder.desc.setText(datasBean.getTitle());
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(position);
                return false;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemclicklistener!=null){
                    onItemclicklistener.onItemclicklistener(datasBean);
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
    public interface onItemclicklistener{
        void onItemclicklistener(RcyBean.DataBean.DatasBean bean);
    }
    private onItemclicklistener onItemclicklistener;

    public void setOnItemclicklistener(RcyAdapter.onItemclicklistener onItemclicklistener) {
        this.onItemclicklistener = onItemclicklistener;
    }
}
