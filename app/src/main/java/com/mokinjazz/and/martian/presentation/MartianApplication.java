package com.mokinjazz.and.martian.presentation;

import android.app.Application;
import android.util.Log;

import com.mokinjazz.and.martian.presentation.di.component.ApplicationComponent;
import com.mokinjazz.and.martian.presentation.di.component.DaggerApplicationComponent;
import com.mokinjazz.and.martian.presentation.di.component.DaggerUserComponent;
import com.mokinjazz.and.martian.presentation.di.component.UserComponent;
import com.mokinjazz.and.martian.presentation.di.module.AppModule;
import com.mokinjazz.and.martian.presentation.di.module.UserModule;

/**
 * Created by vvasilyev on 2/20/16.
 */
public class MartianApplication extends Application{

    private UserComponent  userComponent;

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();

        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(applicationComponent)
                .userModule(new UserModule())
                .build();
    }


    public UserComponent getUserComponent() {
        return userComponent;
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


    public void use(UserComponent component) {
        this.userComponent = component;
    }
}
