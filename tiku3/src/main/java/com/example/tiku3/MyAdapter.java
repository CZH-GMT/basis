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
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<BannerBean.DataBean> bannerlist = new ArrayList<>();
    private List<Bean.ResultsBean> list = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void addbanneritem(List<BannerBean.DataBean> bannerlist) {
        this.bannerlist.addAll(bannerlist);
        notifyDataSetChanged();
    }

    public void additem(List<Bean.ResultsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.itembanner, parent, false);
            return new ViewHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            return new ViewHolder1(inflate);


        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position==0){
            ViewHolder viewHolder= (ViewHolder) holder;
            viewHolder.banner.setImages(bannerlist)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            BannerBean.DataBean bean = (BannerBean.DataBean) path;
                            Glide.with(context).load(bean.getImagePath()).into(imageView);
                        }
                    }).start();
        }else {
            ViewHolder1 viewHolder1= (ViewHolder1) holder;
            Bean.ResultsBean resultsBean = list.get(position-1);
            Glide.with(context).load(resultsBean.getUrl()).into(viewHolder1.iv);
            viewHolder1.tv.setText(resultsBean.getDesc());
            viewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onitemclicklistener!=null){
                        onitemclicklistener.onitemclick();
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    public static


    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public Banner banner;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.banner = (Banner) rootView.findViewById(R.id.banner);
        }

    }
    public static

    class ViewHolder1 extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView iv;
        public TextView tv;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
            this.tv = (TextView) rootView.findViewById(R.id.tv);
        }

    }
    public interface onitemclicklistener{
        void onitemclick();
    }
    private onitemclicklistener onitemclicklistener;

    public void setOnitemclicklistener(MyAdapter.onitemclicklistener onitemclicklistener) {
        this.onitemclicklistener = onitemclicklistener;
    }
}
