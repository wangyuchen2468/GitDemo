package com.example.myapplication.mvp.presenter;

import android.util.Log;

import com.example.myapplication.mvp.contact.ChildCategoryContact;
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.ChildCateRightEntity;
import com.example.myapplication.mvp.model.entity.ChildCategoryEntity;
import com.example.myapplication.mvp.model.entity.ShoesEntity;
import com.example.myapplication.mvp.model.utils.RetrofitThreadManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author ：created by
 * Create Date ：2019/12/6 14
 * Package_Name : yoho
 */
@ActivityScope
public class ChildPresenter extends BasePresenter<ChildCategoryContact.IChildCateModel, ChildCategoryContact.IChildCateView> {

    @Inject
    public ChildPresenter(ChildCategoryContact.IChildCateModel model, ChildCategoryContact.IChildCateView rootView) {
        super(model, rootView);
    }


    public void postLeft(){
        Observable<ChildCategoryEntity> left = mModel.getLeft("1");
        RetrofitThreadManager.getInstance().async(left, new Observer<ChildCategoryEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ChildCategoryEntity childCategoryEntity) {
                mRootView.onSuccessLeft(childCategoryEntity);
            }

            @Override
            public void onError(Throwable e) {
                mRootView.onError();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void postRight(String str){
        Observable<ChildCateRightEntity> right = mModel.getRight(str);
        RetrofitThreadManager.getInstance().async(right, new Observer<ChildCateRightEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ChildCateRightEntity childCateRightEntity) {
                mRootView.onSuccessRight(childCateRightEntity);
            }

            @Override
            public void onError(Throwable e) {
                mRootView.onError();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void postBrandList(String request){
        Observable<BrandListEntity> brandList = mModel.getBrandList(request);
        RetrofitThreadManager.getInstance().async(brandList, new Observer<BrandListEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BrandListEntity brandListEntity) {
                Log.i("###", "onNext:p层 "+brandListEntity.getValues().size());
                if (mRootView!=null)
                    mRootView.onSuccessBrand(brandListEntity);
            }

            @Override
            public void onError(Throwable e) {
                mRootView.onError();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void postShoes(String page){
        Observable<ShoesEntity> shoesEntityObservable = mModel.postShoes(page);
        RetrofitThreadManager.getInstance().async(shoesEntityObservable, new Observer<ShoesEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ShoesEntity shoesEntity) {
                if (mRootView!=null)
                    mRootView.onSuccessShoes(shoesEntity);
            }

            @Override
            public void onError(Throwable e) {
                mRootView.onError();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
