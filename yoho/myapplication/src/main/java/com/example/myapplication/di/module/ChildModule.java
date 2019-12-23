package com.example.myapplication.di.module;

import com.example.myapplication.mvp.contact.ChildCategoryContact;
import com.example.myapplication.mvp.model.ChildModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ChildModule {

    private ChildCategoryContact.IChildCateView iChildCateView;

    public ChildModule(ChildCategoryContact.IChildCateView iChildCateView) {
        this.iChildCateView = iChildCateView;
    }

    @ActivityScope
    @Provides
    ChildCategoryContact.IChildCateModel providerModel(ChildModel model){
        return model;
    }

    @ActivityScope
    @Provides
    ChildCategoryContact.IChildCateView providerView(){
        return iChildCateView;
    }



}
