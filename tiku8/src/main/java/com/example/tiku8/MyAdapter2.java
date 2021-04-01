package com.example.tiku8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

class MyAdapter2 extends RecyclerView.Adapter {
    private Context context;

    public List<Student> list = new ArrayList<>();


    public MyAdapter2(Context context) {
        this.context = context;
    }


    public void additem(List<Student> list) {
        this.list.addAll(list);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new ViewHolder(inflate);

    }
    public int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ViewHolder viewHolder = (ViewHolder) holder;
        Student student = list.get(position);
        Glide.with(context).load(student.getImage()).into(viewHolder.iv);
        viewHolder.age.setText(student.getAge());
        viewHolder.tv.setText(student.getName());
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(position);
                return false;
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }




    public static
    class ViewHolder extends MyAdapter.ViewHolder1 {
        public View rootView;
        public ImageView iv;
        public TextView tv;
        public TextView age;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
            this.tv = (TextView) rootView.findViewById(R.id.tv);
            this.age = (TextView) rootView.findViewById(R.id.age);
        }

    }
    public void deleteitem(int position){
        list.remove(position);
        notifyDataSetChanged();

    }

}
