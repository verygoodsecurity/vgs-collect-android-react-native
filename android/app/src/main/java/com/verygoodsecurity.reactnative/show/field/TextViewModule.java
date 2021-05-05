package com.verygoodsecurity.reactnative.show.field;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import com.verygoodsecurity.reactnative.show.VGSShowOnCreateViewInstanceListener;

public class TextViewModule extends ReactContextBaseJavaModule {

    private TextViewManager manager;

    public TextViewModule(ReactApplicationContext reactContext, TextViewManager manager) {
        super(reactContext);
        this.manager = manager;
    }

    @Override
    public String getName() {
        return manager.getContentPath();
    }

    @ReactMethod
    public void getContentPath(Callback successCallback) {
//        try {
//            successCallback.invoke(fieldName);
//        } catch (IllegalViewOperationException e) { }
    }

}