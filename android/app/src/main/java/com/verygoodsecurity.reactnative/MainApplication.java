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
import android.app.Activity;
import com.verygoodsecurity.reactnative.collect.VGSCollectPackage;
import com.verygoodsecurity.reactnative.collect.field.date.CardExpDatePackage;
import com.verygoodsecurity.reactnative.collect.field.number.CardNumberPackage;
import com.verygoodsecurity.reactnative.collect.OnCreateViewInstanceListener;
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
            // here we bind VGS secure fields with VGSCollect
            VGSCollectPackage collect = new VGSCollectPackage();
            OnCreateViewInstanceListener listener = collect.getListener();
            CardNumberPackage number = new CardNumberPackage(listener);
            CardExpDatePackage expDate = new CardExpDatePackage(listener);

            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new ScanPackage(),
                    number,
                    expDate,
                    collect
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

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