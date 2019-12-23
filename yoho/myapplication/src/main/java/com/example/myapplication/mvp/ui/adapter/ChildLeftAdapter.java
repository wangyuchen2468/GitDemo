package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.ChildCategoryEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：created by
 * Create Date ：2019/12/6 16
 * Package_Name : yoho
 */
public class ChildLeftAdapter  extends RecyclerView.Adapter<ChildVHolder>{
    private List<ChildCategoryEntity.ValuesBean> list = new ArrayList<>();
    private Context context;
    boolean[] booleans;
    private ChildAdapterCallback childAdapterCallback;

    public void setChildAdapterCallback(ChildAdapterCallback childAdapterCallback) {
        this.childAdapterCallback = childAdapterCallback;
    }

    public ChildLeftAdapter(Context context) {
        this.context = context;
    }

    public List<ChildCategoryEntity.ValuesBean> getList() {
        return list;
    }

    public void setList(List<ChildCategoryEntity.ValuesBean> list) {
        this.list = list;
        notifyDataSetChanged();
        booleans = new boolean[this.list.size()];
        for (int i = 0; i < booleans.length; i++) {
            if(i == 0){
                booleans[i] = true;
            }else {
                booleans[i] = false;
            }

        }
    }

    @NonNull
    @Override
    public ChildVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.child_left_item, parent, false);
        return new ChildVHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildVHolder holder, int position) {
        holder.ittem.setChecked( booleans[position]);
        holder.textView.setText(list.get(position).getName());

        if( booleans[position]){
            holder.textView.setTextSize(18);
        }else {
            holder.textView.setTextSize(15);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unCheckedOther(position);
                childAdapterCallback.refreshRight(position);
            }
        });
    }

    private void unCheckedOther(int position) {
        for (int i = 0; i < list.size(); i++) {
            if(i == position){
                booleans[i] = true;
            }else {
                booleans[i] = false;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ChildAdapterCallback{
        void refreshRight(int position);
    }
}
class ChildVHolder extends RecyclerView.ViewHolder{
    RadioButton ittem;
    TextView textView;

    public ChildVHolder(@NonNull View itemView) {
        super(itemView);
        ittem = itemView.findViewById(R.id.child_item_tiao);
        textView = itemView.findViewById(R.id.child_item_txt);
    }
}
