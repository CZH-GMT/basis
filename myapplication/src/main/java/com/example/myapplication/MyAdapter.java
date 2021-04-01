package com.example.myapplication;

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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Bean.ResultsDTO> list = new ArrayList<>();


    public MyAdapter(Context context) {
        this.context = context;
    }

    public void getitem(List<Bean.ResultsDTO> list) {
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }
    private int position;

    public MyAdapter(int position) {
        this.position = position;
    }
    public int getPosition(){
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bean.ResultsDTO resultsDTO = list.get(position);
        Glide.with(context).load(resultsDTO.getUrl()).into(holder.iv);
        holder.title.setText(resultsDTO.getType());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            title=itemView.findViewById(R.id.title);

        }
    }
    public void delete(int position){

        this.list.remove(position);
        notifyDataSetChanged();
    }
    public void undate(int position,String type){
        Bean.ResultsDTO resultsDTO = list.get(position);
        resultsDTO.setDesc(type);
        notifyDataSetChanged();
    }
    public Bean.ResultsDTO getResultBean(int position){
        return list.get(position);
    }
}
