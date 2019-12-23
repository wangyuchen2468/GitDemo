package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.util.Log;
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
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.ShoesEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：created by
 * Create Date ：2019/12/9 11
 * Package_Name : yoho
 */
public class BrandListHorAdapter extends RecyclerView.Adapter<BrandListHorVHolder>{
    private Context mContext;
    private List<BrandListEntity.ValuesBean> list = new ArrayList<>();

    public BrandListHorAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public List<BrandListEntity.ValuesBean> getList() {
        return list;
    }

    public void setList(List<BrandListEntity.ValuesBean> list) {
        this.list = list;
        if(list!=null){
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public BrandListHorVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.brand_list_item, parent, false);
        return new BrandListHorVHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandListHorVHolder holder, int position) {
        Log.i("###shoes", "onBindViewHolder: "+list.get(position).getBrand_name());
        holder.name.setText(list.get(position).getBrand_name());
        Glide.with(mContext).load(Api.APP_DOMAIN+list.get(position).getBrand_icon()).into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class BrandListHorVHolder extends RecyclerView.ViewHolder{

     ImageView pic;
     TextView name;
    public BrandListHorVHolder(@NonNull View itemView) {
        super(itemView);
        pic=  itemView.findViewById(R.id.brand_list_item_pic);
        name=  itemView.findViewById(R.id.brand_list_item_name);
    }
}
