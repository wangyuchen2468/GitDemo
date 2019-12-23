package com.example.myapplication.mvp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.BrandListEntity;

import java.util.List;

/**
 * @author ：created by
 * Create Date ：2019/12/10 09
 * Package_Name : yoho
 */
public class BrandListSecAdapter  extends RecyclerView.Adapter<BrandListSecVHolder>{

    private List<BrandListEntity.ValuesBean> list;

    public BrandListSecAdapter(List<BrandListEntity.ValuesBean> list) {
        this.list = list;
    }

    public List<BrandListEntity.ValuesBean> getList() {
        return list;
    }

    public void setList(List<BrandListEntity.ValuesBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BrandListSecVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_list_sec_item, parent, false);

        return new BrandListSecVHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandListSecVHolder holder, int position) {
            holder.litter.setText(list.get(position).getBrand_letter());
            holder.name.setText(list.get(position).getBrand_name());
            if (!list.get(position).getHot_tag().equals("true")){
                holder.hotPic.setVisibility(View.GONE);
            }else {
                holder.hotPic.setVisibility(View.VISIBLE);
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class BrandListSecVHolder extends RecyclerView.ViewHolder{
    TextView litter;
    TextView name;
    ImageView hotPic;

    public BrandListSecVHolder(@NonNull View itemView) {
        super(itemView);
        litter = itemView.findViewById(R.id.brand_list_sec_item_litter);
        name = itemView.findViewById(R.id.brand_list_sec_item_name);
        hotPic = itemView.findViewById(R.id.brand_list_sec_item_pic);

    }
}
