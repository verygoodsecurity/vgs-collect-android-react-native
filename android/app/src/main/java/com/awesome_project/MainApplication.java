package com.awesome_project;

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
import com.awesome_project.collect.VGSCollectPackage;
import com.awesome_project.fields.holder.CardHolderPackage;
import com.awesome_project.fields.cvc.CardCVCPackage;
import com.awesome_project.fields.date.CardExpDatePackage;
import com.awesome_project.fields.number.CardNumberPackage;
import com.awesome_project.fields.OnCreateViewInstanceListener;
import android.util.Log;
import com.awesome_project.scanner.ScanPackage;

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
            CardHolderPackage holder = new CardHolderPackage(listener);
            CardCVCPackage cvc = new CardCVCPackage(listener);
            CardExpDatePackage expDate = new CardExpDatePackage(listener);

            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new ScanPackage(),
                    number,
                    holder,
                    cvc,
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
