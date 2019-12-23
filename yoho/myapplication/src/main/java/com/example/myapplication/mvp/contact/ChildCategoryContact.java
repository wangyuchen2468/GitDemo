package com.example.myapplication.mvp.contact;

import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.ChildCateRightEntity;
import com.example.myapplication.mvp.model.entity.ChildCategoryEntity;
import com.example.myapplication.mvp.model.entity.ShoesEntity;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;

/**
 * @author ：created by
 * Create Date ：2019/12/6 11
 * Package_Name : yoho
 */
public interface ChildCategoryContact {
    interface IChildCateModel extends IModel {
        Observable<ChildCategoryEntity> getLeft(String request);
        Observable<ChildCateRightEntity> getRight(String request);
        Observable<BrandListEntity> getBrandList(String request);
        Observable<ShoesEntity> postShoes(String request);
    }

    interface IChildCateView extends IView{
        void onSuccessLeft(ChildCategoryEntity childCategoryEntity);
        void onSuccessRight(ChildCateRightEntity childCateRightEntity);
        void onSuccessBrand(BrandListEntity brandListEntity);
        void onSuccessShoes(ShoesEntity shoesEntity);
        void onError();
    }
}
