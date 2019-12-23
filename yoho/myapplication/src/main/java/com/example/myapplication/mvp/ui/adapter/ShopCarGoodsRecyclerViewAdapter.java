package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.CarListEntity;
import com.example.myapplication.mvp.model.utils.ShopCarListener;

import java.util.ArrayList;

/**
 * @ProjectName: yoho
 * @Package: com.example.zhq_yoho.mvp.ui.adapter
 * @ClassName: ShopCarGoodsRecyclerViewAdapter
 * @Description: java类作用描述
 * @Author: 周贺棋
 * @CreateDate: 2019/12/17 14:42
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/12/17 14:42
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ShopCarGoodsRecyclerViewAdapter extends RecyclerView.Adapter<ShopCarGoodsRecyclerViewAdapter.ShopCarGoodsViewHolder> {
    ArrayList<Boolean> list_select;
    ArrayList<CarListEntity.ValuesBean> list_user;
    Context context;
    public Boolean change = false;
    ShopCarListener shopCarListener;

    public void setShopCarListener(ShopCarListener shopCarListener) {
        this.shopCarListener = shopCarListener;
    }

    public ShopCarGoodsRecyclerViewAdapter(ArrayList<Boolean> list_select, ArrayList<CarListEntity.ValuesBean> list_user, Context context) {
        this.list_select = list_select;
        this.list_user = list_user;
        this.context = context;
    }

    @NonNull
    @Override
    public ShopCarGoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopCarGoodsViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_cargoods,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopCarGoodsViewHolder holder, int position) {
        if (change){
            holder.text_title.setVisibility(View.GONE);
            holder.linearLayout.setVisibility(View.VISIBLE);
        }else{
            holder.text_title.setVisibility(View.VISIBLE);
            holder.linearLayout.setVisibility(View.GONE);
        }
            Glide.with(context).load(Api.APP_DOMAIN+list_user.get(position).getCar_path()).into(holder.imageView);
            holder.text_title.setText(list_user.get(position).getShop_name());
            holder.text_num.setText(list_user.get(position).getShop_num());
            holder.text_money.setText("￥"+list_user.get(position).getShop_price());
            holder.text_color.setText("颜色："+list_user.get(position).getShop_color()+" 尺码："+list_user.get(position).getShop_size());
            holder.checkBox.setChecked(list_select.get(position));
            holder.img_jia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.text_num.setText(Integer.parseInt(holder.text_num.getText().toString())+1+"");
                    shopCarListener.add(holder.checkBox.isChecked(),position);
                }
            });
            holder.img_jian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Integer.parseInt(holder.text_num.getText().toString())==1){
                        Toast.makeText(context, "商品数量最少为1", Toast.LENGTH_SHORT).show();
                    }else{
                        holder.text_num.setText(Integer.parseInt(holder.text_num.getText().toString())-1+"");
                        shopCarListener.remove(holder.checkBox.isChecked(),position);
                    }
                }
            });
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shopCarListener.isSelect(holder.checkBox.isChecked(),position);
                }
            });






    }

    @Override
    public int getItemCount() {
        return list_user.size();
    }

    class ShopCarGoodsViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        ImageView imageView;
        TextView text_title;
        TextView text_color;
        TextView text_money;
        TextView text_num;
        LinearLayout linearLayout;
        TextView text_change;
        ImageView img_jian;
        ImageView img_jia;
        public ShopCarGoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.iv_adapter_cargoods_select);
            imageView = itemView.findViewById(R.id.iv_adapter_cargoods_img);
            text_title = itemView.findViewById(R.id.iv_adapter_cargoods_title);
            text_color = itemView.findViewById(R.id.iv_adapter_cargoods_color);
            text_money = itemView.findViewById(R.id.iv_adapter_cargoods_money);
            text_num = itemView.findViewById(R.id.iv_adapter_cargoods_num);
            linearLayout = itemView.findViewById(R.id.iv_adapter_cargoods_linearlayout);
            text_change = itemView.findViewById(R.id.iv_adapter_cargoods_change);
            img_jian = itemView.findViewById(R.id.iv_adapter_cargoods_jian);
            img_jia = itemView.findViewById(R.id.iv_adapter_cargoods_jia);


        }
    }
}
