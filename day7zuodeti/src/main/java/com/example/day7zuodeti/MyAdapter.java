package com.example.day7zuodeti;

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
    private List<Bean.ResultsBean> list = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;

    }

    public void additem(List<Bean.ResultsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
            return new ViewHolder0(inflate);
        } else if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
            return new ViewHolder1(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item3, parent, false);
            return new ViewHolder2(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Bean.ResultsBean resultsBean = list.get(position);
        if (position == 0) {
            ViewHolder0 viewHolder0 = (ViewHolder0) holder;
            Glide.with(context).load(resultsBean.getUrl_1()).into(viewHolder0.iv1);
            viewHolder0.title1.setText(resultsBean.getTitle());
        } else if (position==1){
            ViewHolder1 viewHolder1 = (ViewHolder1) holder;
            Glide.with(context).load(resultsBean.getUrl_1()).into(viewHolder1.iv2);
            Glide.with(context).load(resultsBean.getUrl_2()).into(viewHolder1.iv3);
            Glide.with(context).load(resultsBean.getUrl_3()).into(viewHolder1.iv4);
            viewHolder1.title2.setText(resultsBean.getTitle());
        }else {
            ViewHolder2 viewHolder2 = (ViewHolder2) holder;
            Glide.with(context).load(resultsBean.getUrl_1()).into(viewHolder2.iv5);
            viewHolder2.title5.setText(resultsBean.getTitle());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder0 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv1;
        public TextView title1;

        public ViewHolder0(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv1 = (ImageView) rootView.findViewById(R.id.iv1);
            this.title1 = (TextView) rootView.findViewById(R.id.title1);
        }

    }

    public static
    class ViewHolder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView title2;
        public ImageView iv2;
        public ImageView iv3;
        public ImageView iv4;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.title2 = (TextView) rootView.findViewById(R.id.title2);
            this.iv2 = (ImageView) rootView.findViewById(R.id.iv2);
            this.iv3 = (ImageView) rootView.findViewById(R.id.iv3);
            this.iv4 = (ImageView) rootView.findViewById(R.id.iv4);
        }

    }

    public static
    class ViewHolder2 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView title5;
        public ImageView iv5;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.title5 = (TextView) rootView.findViewById(R.id.title5);
            this.iv5 = (ImageView) rootView.findViewById(R.id.iv5);
        }

    }
}
