package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.MyGoodsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：created by
 * Create Date ：2019/12/11 09
 * Package_Name : yoho
 */
    public class KingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<MyGoodsEntity> list ;
    private int insideItemCount ;
    private ArrayList<Integer> mHeader;
    private ArrayList<Integer> mFooter;
    private LayoutInflater mLayoutInflater;

    int headerCount = 0;
    int footerCount = 0;
    int listCount = 1;

//    头尾布局的游标
    int headNonius = 0;
    int footNonius = 0;

    private KingViewCallBack callBack;
    int callbackId = -1;

    public void setCallBack(int callbackId,KingViewCallBack callBack) {
        this.callbackId = callbackId;
        this.callBack = callBack;
        notifyDataSetChanged();
    }

    public KingAdapter(Context mContext, List<MyGoodsEntity> list, int insideItemCount) {
        this.mContext = mContext;
        this.list = list;
        this.insideItemCount = insideItemCount;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public KingAdapter(Context mContext, List<MyGoodsEntity> list, int insideItemCount, ArrayList<Integer> mHeader, ArrayList<Integer> mFooter) {
        this.mContext = mContext;
        this.list = list;
        this.insideItemCount = insideItemCount;
        this.mHeader = mHeader;
        this.mFooter = mFooter;
        if(mHeader!=null){
            headerCount = mHeader.size();
        }
        if(mFooter!=null){
            footerCount = mFooter.size();
        }
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    private static final int ISHEADER = 0;
    private static final int ISFOOTER = 1;
    private static final int ISUSUALLY = 2;

    // 刷新
    public void refreshList(List<MyGoodsEntity> list) {
        this.list = list;
        headNonius = 0;
        footNonius = 0;
        notifyDataSetChanged();
    }
    // 加载更多
    public void loadMore(List<MyGoodsEntity> list){
        this.list.addAll(list);
        headNonius = 0;
        footNonius = 0;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ISHEADER ){
            Integer integer = mHeader.get(headNonius++);
            View view = mLayoutInflater.inflate(integer, parent, false);
            if(integer == callbackId){
                callBack.onKingBindView(view);
            }
            return new KingHeaderVHolder(view);
        }
        if(viewType == ISFOOTER){
            Integer integer = mHeader.get(footNonius++);
            View view = mLayoutInflater.inflate(integer, parent, false);
            return new KingHeaderVHolder(view);
        }

        View view = mLayoutInflater.inflate(R.layout.king_view, parent, false);
        return new KingVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof KingVHolder){
            if(insideItemCount == 1)
                ((KingVHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            else
                ((KingVHolder) holder).recyclerView.setLayoutManager(new GridLayoutManager(mContext,insideItemCount));

            ((KingVHolder) holder).recyclerView.setAdapter(new KingInsideAdapter(mContext,list));
        }
    }

    @Override
    public int getItemCount() {
        int sum = 0;
        if (headerCount !=0) {
            sum += headerCount;
        }
        if(footerCount!=0){
            sum += footerCount;
        }
        sum+=list.size();
        return sum;
    }


    @Override
    public int getItemViewType(int position) {
        if(headerCount!=0 && position<headerCount){
            return ISHEADER;
        }

        if(footerCount!=0&&position>=(headerCount+listCount)){
            return ISFOOTER;
        }

        return ISUSUALLY;
    }


    public interface KingViewCallBack{
        void onKingBindView(View view);
    }
}

class KingVHolder extends RecyclerView.ViewHolder{

     RecyclerView recyclerView;
    public KingVHolder(@NonNull View itemView) {
        super(itemView);
        recyclerView = itemView.findViewById(R.id.king_rec);
    }
}

class KingHeaderVHolder extends RecyclerView.ViewHolder{

    public KingHeaderVHolder(@NonNull View itemView) {
        super(itemView);
    }
}