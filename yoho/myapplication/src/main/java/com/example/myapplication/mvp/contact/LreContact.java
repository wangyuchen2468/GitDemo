package com.example.myapplication.mvp.contact;

import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;

public interface LreContact {
    interface LreView extends IView{
        void success(BaseEntity entity,int type);
        void error(String error);
        void refreshSuccess(BaseEntity entity);
        void loadSuceess(BaseEntity entity);
    }
    interface LreModel extends IModel{
        Observable<BaseEntity> lreRequest(String params,int type);
        Observable<BaseEntity> lreRefreshRequest(String params,int type);
        Observable<BaseEntity> lreLoadRequest(String params,int type);
    }

}
