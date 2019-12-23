package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.ChildCateRightEntity;
import com.example.myapplication.mvp.model.entity.MyGoodsEntity;
import com.example.myapplication.mvp.ui.activity.GoodsValuesActivity;

import java.util.List;

/**
 * @author ：created by
 * Create Date ：2019/12/7 11
 * Package_Name : yoho
 */
public class KingInsideAdapter extends RecyclerView.Adapter<KingInsideAdapter.ChildRightInsideVHolder>{
    private Context mContext;
    private List<MyGoodsEntity> list;

    public KingInsideAdapter(Context mContext, List<MyGoodsEntity> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @NonNull
    @Override
    public ChildRightInsideVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.king_goods_item, parent, false);
        return new ChildRightInsideVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildRightInsideVHolder holder, int position) {
        holder.price.setText("￥"+list.get(position).getPrice());
        holder.sellnum.setText("100人购买");
        holder.name.setText(list.get(position).getName());
        Glide.with(mContext).load(Api.APP_DOMAIN+list.get(position).getUrlPath()).into(holder.pic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, GoodsValuesActivity.class).putExtra("goodsid",list.get(position).getCategoryId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ChildRightInsideVHolder extends RecyclerView.ViewHolder {
        ImageView pic;
        TextView name;
        TextView price;
        TextView sellnum;
        public ChildRightInsideVHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.king_goods_name);
            pic = itemView.findViewById(R.id.king_goods_pic);
            price = itemView.findViewById(R.id.king_goods_price);
            sellnum = itemView.findViewById(R.id.king_goods_sellnum);
        }
    }
}


