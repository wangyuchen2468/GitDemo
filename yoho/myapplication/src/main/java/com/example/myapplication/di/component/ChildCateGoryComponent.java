package com.example.myapplication.di.component;

import com.example.myapplication.di.module.ChildModule;
import com.example.myapplication.di.module.HomeModule;
import com.example.myapplication.mvp.ui.fragment.child.Classify_BrandFragment;
import com.example.myapplication.mvp.ui.fragment.child.Classify_CategoryFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * @author ：created by
 * Create Date ：2019/12/6 11
 * Package_Name : yoho
 */

@ActivityScope
@Component(modules = ChildModule.class,dependencies = AppComponent.class)
public interface ChildCateGoryComponent {
    void inject(Classify_CategoryFragment classify_categoryFragment);
    void inject(Classify_BrandFragment classify_brandFragment);
}
