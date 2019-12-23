package com.example.myapplication.mvp.model.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * @ProjectName: yoho
 * @Package: com.example.zhq_yoho.util
 * @ClassName: GoodsValuesHeadView
 * @Description: java类作用描述
 * @Author: 周贺棋
 * @CreateDate: 2019/12/18 14:09
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/12/18 14:09
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class GoodsValuesHeadView extends RelativeLayout {
    public GoodsValuesHeadView(Context context) {
        super(context);
    }

    public GoodsValuesHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.goodsvalueshead, this,true);
        ImageView detailfanhui=view.findViewById(R.id.detailfanhui);
        ImageView detaildiandian=view.findViewById(R.id.detaildiandian);
        ImageView detailzhuanfa=view.findViewById(R.id.detailzhuanfa);
        TextView detailtv=view.findViewById(R.id.detailtv);
    }


}
