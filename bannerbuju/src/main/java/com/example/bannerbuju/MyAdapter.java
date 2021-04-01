package com.example.bannerbuju;

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
    private List<ListBean.DataBean.DatasBean> list = new ArrayList<>();
    private List<BannerBean.DataBean> bannerlist = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void addItem(List<ListBean.DataBean.DatasBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void addbanneritem(List<BannerBean.DataBean> bannerlist){
        this.bannerlist.addAll(bannerlist);
        notifyDataSetChanged();
    }

    private final static int VIEW_TYPE_0 = 0;
    private final static int VIEW_TYPE_1 = 1;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_0;
        } else {
            return VIEW_TYPE_1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_banner, parent, false);

            return new ViewHolder0(inflate);
        }else {
            View inflate1 = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
            return new ViewHolder1(inflate1);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type==VIEW_TYPE_0){

            ViewHolder0 viewHolder0= (ViewHolder0) holder;
//            viewHolder0.banner.setImages(bannerlist)
//                    .setImageLoader(new ImageLoader() {
//                        @Override
//                        public void displayImage(Context context, Object path, ImageView imageView) {
//                            BannerBean.DataBean dataBean= (BannerBean.DataBean) path;
//                            String imagePath = dataBean.getImagePath();
//                            Glide.with(context).load(imagePath).into(imageView);
//                        }
//                    })
//                    .start();
            viewHolder0.banner.setImages(bannerlist).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    BannerBean.DataBean bannerBean= (BannerBean.DataBean) path;
                    Glide.with(context).load(bannerBean.getImagePath()).into(imageView);
                }
            }).start();

        }else {
            ListBean.DataBean.DatasBean datasBean = list.get(position-1);
            ViewHolder1 viewHolder1= (ViewHolder1) holder;
            viewHolder1.tv.setText(datasBean.getDesc());


        }

    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public static
    class ViewHolder0 extends RecyclerView.ViewHolder{
        public View rootView;
       private Banner banner;

        public ViewHolder0(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.banner = (Banner) rootView.findViewById(R.id.banner);
        }

    }

    public static
    class ViewHolder1 extends RecyclerView.ViewHolder{
        public View rootView;
        public TextView tv;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv = (TextView) rootView.findViewById(R.id.tv);
        }

    }
}
