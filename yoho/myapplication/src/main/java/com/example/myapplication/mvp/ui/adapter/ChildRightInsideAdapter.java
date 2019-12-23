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
import com.example.myapplication.mvp.ui.activity.GoodsValuesActivity;
import com.youth.banner.Banner;

import java.util.List;

/**
 * @author ：created by
 * Create Date ：2019/12/7 11
 * Package_Name : yoho
 */
public class ChildRightInsideAdapter  extends RecyclerView.Adapter<ChildRightInsideAdapter.ChildRightInsideVHolder>{
    private Context mContext;
    private List<ChildCateRightEntity.ValuesBean> list;

    public ChildRightInsideAdapter(Context mContext, List<ChildCateRightEntity.ValuesBean> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @NonNull
    @Override
    public ChildRightInsideVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.child_right_item, parent, false);
        return new ChildRightInsideVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildRightInsideVHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        Glide.with(mContext).load(Api.APP_DOMAIN+list.get(position).getImage_path()).into(holder.pic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, GoodsValuesActivity.class).putExtra("goodsid",list.get(position).getCategory_id()));
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
        public ChildRightInsideVHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.child_right_item_name);
            pic = itemView.findViewById(R.id.child_right_item_pic);
        }
    }
}


