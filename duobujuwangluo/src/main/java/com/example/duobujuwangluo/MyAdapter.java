package com.example.duobujuwangluo;

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
    private List<Bean.ResultsDTO> list=new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }
    public void addItem(List<Bean.ResultsDTO> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return 0;

        }else {
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      if (viewType==1){
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
        if (type==1){

            viewHolder0 viewHolder0= (MyAdapter.viewHolder0) holder;
            Bean.ResultsDTO resultsDTO = list.get(position);
            Glide.with(context).load(resultsDTO.getUrl()).into(viewHolder0.iv);
            viewHolder0.title.setText(resultsDTO.getDesc());
        }else {
            viewHolder1 viewHolder1= (MyAdapter.viewHolder1) holder;
            Bean.ResultsDTO resultsDTO = list.get(position);
            Glide.with(context).load(resultsDTO.getUrl()).into(viewHolder1.iv);
            viewHolder1.title.setText(resultsDTO.getDesc());

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class viewHolder0 extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView title;

        public viewHolder0(View inflate) {
            super(inflate);
            iv=inflate.findViewById(R.id.iv);
            title=inflate.findViewById(R.id.title);

        }
    }

    private class viewHolder1 extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView title;

        public viewHolder1(View inflate) {
            super(inflate);
            iv=inflate.findViewById(R.id.iv);
            title=inflate.findViewById(R.id.title);
        }
    }
}
