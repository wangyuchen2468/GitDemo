package com.example.myapplication.di.component;

import com.example.myapplication.di.module.MenuModule;
import com.example.myapplication.mvp.ui.activity.MainActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = MenuModule.class,dependencies = AppComponent.class)
public interface MenuComponent {
    void inject(MainActivity mainActivity);
}
