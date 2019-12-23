package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.StaggBean;

import java.util.ArrayList;

public class MyTabRecyclerAdpater extends RecyclerView.Adapter<MyTabRecyclerAdpater.baseview> {

    ArrayList<GoodsListEntity.ValuesBean> list;
    Context context;

    public MyTabRecyclerAdpater(ArrayList<GoodsListEntity.ValuesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_stagglayout3, parent, false);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {
        holder.textView.setText(list.get(position).getGoods_name());
        Glide.with(context).load("http://169.254.105.174/yoho"+list.get(position).getGoods_img_path()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class baseview extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public baseview(@NonNull View itemView) {
            super(itemView);
            this.imageView= itemView.findViewById(R.id.stagg_image3);
            this.textView= itemView.findViewById(R.id.stagg_text3);
        }
    }
}
