package com.example.day5zao;

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

public class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<BannerBean.DataBean> bannerlist = new ArrayList<>();
    private List<ListBean.DataBean.DatasBean> list = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void additem(List<ListBean.DataBean.DatasBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addbanneritem(List<BannerBean.DataBean> bannerlist) {
        this.bannerlist.addAll(bannerlist);
        notifyDataSetChanged();
    }



    public int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public MyAdapter(int position) {
        this.position = position;

    }





    private final static int VIEW_TYPE_0 = 0;
    private final static int VIEW_TYPE_1 = 1;

    @Override
    public int getItemViewType(int position) {
        if (position == VIEW_TYPE_0) {
            return VIEW_TYPE_0;
        } else {
            return VIEW_TYPE_1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.itembanner, parent, false);
            return new ViewHolder0(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.itemlayout, parent, false);
            return new ViewHolder1(inflate);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        if (type==VIEW_TYPE_0){
            ViewHolder0 viewHolder0= (ViewHolder0) holder;
//            final BannerBean.DataBean dataBean = bannerlist.get(position);
            viewHolder0.banner.setImages(bannerlist)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            BannerBean.DataBean dataBean= (BannerBean.DataBean) path;
                            Glide.with(context).load(dataBean.getImagePath()).into(imageView);

                        }
                    })
                    .start();
        }else {
            ViewHolder1 viewHolder1= (ViewHolder1) holder;
            ListBean.DataBean.DatasBean datasBean = list.get(position-1);
            viewHolder1.tv.setText(datasBean.getTitle());
          viewHolder1.itemView.setOnLongClickListener(new View.OnLongClickListener() {
              @Override
              public boolean onLongClick(View v) {
                  setPosition(position-1);
                  return false;
              }
          });
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
    class ViewHolder1 extends RecyclerView.ViewHolder{
        public View rootView;
        public TextView tv;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv = (TextView) rootView.findViewById(R.id.tv);
        }

    }

    public void delete(int position){
        list.remove(position);
        notifyDataSetChanged();
    }
}
