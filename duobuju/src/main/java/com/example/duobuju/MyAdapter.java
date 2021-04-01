package com.example.duobuju;

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
    private List<Integer> list=new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }


//    @Override
//    public int getItemViewType(int position) {
//        if (position %2 ==0){
//            return 0;
//        }else {
//            return 1;
//        }
//    }

    public void additem(ArrayList<Integer> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==0){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item0, parent, false);
            return new viewHolder0(inflate);
        }else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
            return new viewHolder1(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type==0){
            viewHolder0 viewHolder0 = (viewHolder0) holder;
          viewHolder0.banner.setImages(list).setImageLoader(new ImageLoader() {
              @Override
              public void displayImage(Context context, Object path, ImageView imageView) {
                  Glide.with(context).load(path).into(imageView);
              }
          }).start();
        }else {
            viewHolder1 viewHolder1 = (viewHolder1) holder;
            Integer integer = list.get(position);
            Glide.with(context).load(integer).into(viewHolder1.iv);
            viewHolder1.title.setText(integer);

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class viewHolder0 extends RecyclerView.ViewHolder {
       private Banner banner;
        public viewHolder0(View p0) {
            super(p0);

            banner =(Banner) p0.findViewById(R.id.banner);

        }
    }

    private class viewHolder1 extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView title;
        public viewHolder1(View inflate) {
            super(inflate);

            iv =(ImageView) inflate.findViewById(R.id.iv);
            title=(TextView) inflate.findViewById(R.id.title);
        }
    }
}
