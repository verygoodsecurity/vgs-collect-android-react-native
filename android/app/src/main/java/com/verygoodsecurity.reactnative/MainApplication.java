package com.verygoodsecurity.reactnative;

import android.app.Application;
import android.content.Context;

import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import android.app.Activity;

import com.verygoodsecurity.reactnative.show.VGSShowPackage;
import com.verygoodsecurity.reactnative.show.field.TextViewPackage;
import com.verygoodsecurity.reactnative.show.VGSShowOnCreateViewInstanceListener;

import com.verygoodsecurity.reactnative.collect.VGSCollectPackage;
import com.verygoodsecurity.reactnative.collect.field.date.CardExpDatePackage;
import com.verygoodsecurity.reactnative.collect.field.number.CardNumberPackage;
import com.verygoodsecurity.reactnative.collect.VGSCollectOnCreateViewInstanceListener;

import android.util.Log;

import com.verygoodsecurity.reactnative.collect.scanner.ScanPackage;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            ArrayList<ReactPackage> packages = new ArrayList<>();
            packages.add(new MainReactPackage());

            getCollectPackages(packages);
            getShowPackages(packages);

            return packages;
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

    private void getShowPackages(ArrayList<ReactPackage> packages) {
        // here we bind VGS secure fields with VGSShow
        VGSShowPackage show = new VGSShowPackage();
        VGSShowOnCreateViewInstanceListener listener = show.getListener();

        ReactPackage[] array = new ReactPackage[] {
                new TextViewPackage(listener),
                show
        };

        packages.addAll(Arrays.<ReactPackage>asList(array));
    }

    private void getCollectPackages(ArrayList<ReactPackage> packages) {
        // here we bind VGS secure fields with VGSCollect
        VGSCollectPackage collect = new VGSCollectPackage();
        VGSCollectOnCreateViewInstanceListener listener = collect.getListener();

        ReactPackage[] array = new ReactPackage[]{
                new ScanPackage(),
                new CardNumberPackage(listener),
                new CardExpDatePackage(listener),
                collect
        };

        packages.addAll(Arrays.<ReactPackage>asList(array));
    }

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
    }
}
