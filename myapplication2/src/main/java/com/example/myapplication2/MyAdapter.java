//package com.example.myapplication2;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.youth.banner.Banner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyAdapter extends RecyclerView.Adapter {
//    private Context context;
//    private List<Bean.DataBean> bannerlist = new ArrayList<>();
//    private List<>
//
//    public void addItem(List<Bean.DataBean> bannerlist) {
//        this.bannerlist.addAll(bannerlist);
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0) {
//            return 0;
//        } else {
//            return 1;
//        }
//
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (viewType == 0) {
//            View inflate = LayoutInflater.from(context).inflate(R.layout.itembanner, parent, false);
//            return new ViewHolder0(inflate);
//        } else {
//            View inflate = LayoutInflater.from(context).inflate(R.layout.itemrcy, parent, false);
//            return new ViewHolder1(inflate);
//        }
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        Bean.DataBean dataBean = list.get(position);
//        if (position==0){}
//        ViewHolder0 viewHolder0= (ViewHolder0) holder;
//        viewHolder0.banner.setImages(b)
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public static
//    class ViewHolder0 extends RecyclerView.ViewHolder {
//        public View rootView;
//        public Banner banner;
//
//        public ViewHolder0(View rootView) {
//            super(rootView);
//            this.rootView = rootView;
//            this.banner = (Banner) rootView.findViewById(R.id.banner);
//        }
//
//    }
//
//    public static
//    class ViewHolder1 extends RecyclerView.ViewHolder {
//        public View rootView;
//        public Banner banner;
//
//        public ViewHolder1(View rootView) {
//            super(rootView);
//            this.rootView = rootView;
//            this.banner = (Banner) rootView.findViewById(R.id.banner);
//        }
//
//    }
//}
