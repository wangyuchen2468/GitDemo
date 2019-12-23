package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.ZhouSonEntity;

import java.util.ArrayList;

/**
 * @ProjectName: yoho
 * @Package: com.example.zhq_yoho.mvp.ui.adapter
 * @ClassName: ShopcarOptimizationRecyclerViewAdapter
 * @Description: java类作用描述
 * @Author: 周贺棋
 * @CreateDate: 2019/12/17 12:13
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/12/17 12:13
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ShopcarOptimizationRecyclerViewAdapter extends RecyclerView.Adapter<ShopcarOptimizationRecyclerViewAdapter.ShopCarOptimizationViewHolder> {
    ArrayList<ZhouSonEntity.ValuesBean> list;
    Context context;

    public ShopcarOptimizationRecyclerViewAdapter(ArrayList<ZhouSonEntity.ValuesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ShopCarOptimizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopCarOptimizationViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_shopcar,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopCarOptimizationViewHolder holder, int position) {
        Glide.with(context).load(Api.APP_DOMAIN+list.get(position).getGoods_img_path()).into(holder.img_head);
        if (!list.get(position).getGoods_discount_price().isEmpty()){
            holder.text_discount.setTextColor(Color.RED);
            SpannableString spanString = new SpannableString("￥"+list.get(position).getGoods_original_price());
            StrikethroughSpan span = new StrikethroughSpan();
            spanString.setSpan(span, 0, list.get(position).getGoods_original_price().length()+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //尝试用固定下标
            holder.text_original.setText(spanString);
//            holder.text_original.setText("￥"+list_goods.get(position).getGoods_original_price());
        }else{
            holder.text_discount.setTextColor(Color.BLACK);
        }
        holder.text_discount.setText("￥"+list.get(position).getGoods_discount_price());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ShopCarOptimizationViewHolder extends RecyclerView.ViewHolder{
        ImageView img_head;
        TextView text_discount;
        TextView text_original;
        ImageView img_more;
        LinearLayout linearLayout;
        public ShopCarOptimizationViewHolder(@NonNull View itemView) {
            super(itemView);
            img_head = itemView.findViewById(R.id.iv_adapter_shopcar_optimization_img);
            text_discount = itemView.findViewById(R.id.iv_adapter_shopcar_optimization_discound);
            text_original = itemView.findViewById(R.id.iv_adapter_shopcar_optimization_original);
            img_more = itemView.findViewById(R.id.iv_adapter_shopcar_optimization_more);
            linearLayout = itemView.findViewById(R.id.iv_adapter_shopcar_optimization_linearlayout);
        }
    }
}
