package com.example.duobujuerjiliebiao;

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
    private List<BannerBean.DataBean> bannerList = new ArrayList<>();
    private List<RcyBean.DataBean> list = new ArrayList<>();


    public MyAdapter(Context context) {
        this.context = context;
    }

    public void additem(List<RcyBean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    public void addbanneritem(List<BannerBean.DataBean> bannerList) {
        this.bannerList.addAll(bannerList);
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

            return new ViewHolder0(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.itemlayout, parent, false);

            return new ViewHolder1(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);


        if (type==0){

            ViewHolder0 viewHolder0= (ViewHolder0) holder;
            viewHolder0.banner.setImages(bannerList)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            BannerBean.DataBean bean= (BannerBean.DataBean) path;
                            Glide.with(context).load(bean.getImagePath()).into(imageView);


                        }
                    }).start();
        }else {
            RcyBean.DataBean dataBean = list.get(position-1);
            ViewHolder1 viewHolder1= (ViewHolder1) holder;
            Glide.with(context).load(dataBean.getImagePath()).into(viewHolder1.iv);
            viewHolder1.tv.setText(dataBean.getTitle());
        }

    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    public static
    class ViewHolder0 extends RecyclerView.ViewHolder {
        public View rootView;
        public Banner banner;

        public ViewHolder0(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.banner = (Banner) rootView.findViewById(R.id.banner);
        }

    }

    public static
    class ViewHolder1 extends RecyclerView.ViewHolder {
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
}
