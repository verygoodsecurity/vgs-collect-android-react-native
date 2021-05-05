package com.verygoodsecurity.reactnative.show.field;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;

import com.verygoodsecurity.reactnative.show.VGSShowOnCreateViewInstanceListener;

public class TextViewPackage implements ReactPackage {

    private VGSShowOnCreateViewInstanceListener listener;
    private TextViewManager calManager;

    public TextViewPackage(VGSShowOnCreateViewInstanceListener listener) {
        this.listener = listener;
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        if (calManager == null) {
            calManager = new TextViewManager(listener);
        }

        return Arrays.<NativeModule>asList(new TextViewModule(reactContext, calManager));
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        if (calManager == null) {
            calManager = new TextViewManager(listener);
        }

        return Arrays.<ViewManager>asList(calManager);
    }
}