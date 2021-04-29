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
import com.verygoodsecurity.reactnative.fields.holder.CardHolderPackage;
import com.verygoodsecurity.reactnative.fields.cvc.CardCVCPackage;
import com.verygoodsecurity.reactnative.fields.date.CardExpDatePackage;
import com.verygoodsecurity.reactnative.fields.number.CardNumberPackage;
import com.verygoodsecurity.reactnative.fields.OnCreateViewInstanceListener;
import android.util.Log;
import com.verygoodsecurity.reactnative.scanner.ScanPackage;

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
