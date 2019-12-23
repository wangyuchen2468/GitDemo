package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.ChildCateRightEntity;
import com.example.myapplication.mvp.model.utils.BannerImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：created by
 * Create Date ：2019/12/7 09
 * Package_Name : yoho
 */
public class ChildRightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<ChildCateRightEntity.ValuesBean> mData = new ArrayList<>();

    private final static int ITEM_HEADER=0;
    private final static int ITEM_CONTENT=1;
    private final static int ITEM_FOOT=2;
    private ChildRightInsideAdapter adapter;
    /**
     * 头布局和尾布局的数量
     */
    private int mChild = 1;
    private int mHeader=1;
    private int mFoot=1;


    public ChildRightAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public List<ChildCateRightEntity.ValuesBean> getmData() {
        return mData;
    }

    public void setmData(List<ChildCateRightEntity.ValuesBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==ITEM_CONTENT){
            View view = mLayoutInflater.inflate(R.layout.child_right_grid, parent, false);
            return new ChildRightVHolder(view);
        } else if (viewType==ITEM_HEADER){
            View view = mLayoutInflater.inflate(R.layout.child_right_headview, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = mLayoutInflater.inflate(R.layout.child_right_footview, parent, false);
            return new FooterViewHolder(view);
        }
//         else if (viewType==ITEM_FOOT){}
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Log.e("MMM", "onBindViewHolder: "+position);

        if (holder instanceof ChildRightVHolder){
            adapter = new ChildRightInsideAdapter(mContext,mData);
            ((ChildRightVHolder) holder).rec.setLayoutManager(new GridLayoutManager(mContext,3));
            ((ChildRightVHolder) holder).rec.setAdapter(adapter);
        } else if(holder instanceof HeaderViewHolder){
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(R.drawable.man_banner_1);
            integers.add(R.drawable.man_banner_2);
            integers.add(R.drawable.man_banner_3);
            ((HeaderViewHolder) holder).banner.setImages(integers);
            ((HeaderViewHolder) holder).banner.setDelayTime(1000);
            ((HeaderViewHolder) holder).banner.setImageLoader(new BannerImageLoader());
            ((HeaderViewHolder) holder).banner.start();
        }
    }

    @Override
    public int getItemCount() {
        //固定数目,一个头,一个尾
        return mChild+mHeader+mFoot;
    }


    @Override
    public int getItemViewType(int position) {
//        if (mHeader!=0&&position<mHeader){
//            return ITEM_HEADER;
//        }
//        if (mFoot!=0&&position>=mData.size()+mHeader){
//            return ITEM_FOOT;
//        }
//        return ITEM_CONTENT;
        if(position == 0){
            return ITEM_HEADER;
        }else if (position == 2){
            return ITEM_FOOT;
        }
        return ITEM_CONTENT;
    }

    class ChildRightVHolder extends RecyclerView.ViewHolder {
        RecyclerView rec;
        public ChildRightVHolder(View itemView) {
            super(itemView);
            rec=itemView.findViewById(R.id.child_right_grid_iam);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        Banner banner;
        TextView title;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.child_right_head_banner);
            title = itemView.findViewById(R.id.child_right_head_title);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
