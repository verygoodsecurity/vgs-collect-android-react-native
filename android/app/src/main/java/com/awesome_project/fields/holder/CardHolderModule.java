package com.verygoodsecurity.reactnative.fields.holder;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import android.app.Activity;
import com.facebook.react.bridge.Callback;
import com.facebook.react.uimanager.IllegalViewOperationException;
import android.util.Log;
import android.content.Intent;
import com.facebook.react.bridge.Promise;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import com.verygoodsecurity.vgscollect.core.Environment;
import com.verygoodsecurity.vgscollect.core.VGSCollect;
import com.verygoodsecurity.vgscollect.core.HTTPMethod;
import com.verygoodsecurity.reactnative.fields.OnCreateViewInstanceListener;
import com.verygoodsecurity.vgscollect.view.InputFieldView;

public class CardHolderModule extends ReactContextBaseJavaModule {

    private String fieldName;

    public CardHolderModule(ReactApplicationContext reactContext, CardHolderManager calManager) {
        super(reactContext);
        if (calManager != null) {
            fieldName = calManager.getFieldName();
        }
    }

    @Override
    public String getName() {
        return "HolderVGSEditText";
    }

    @ReactMethod
    public void getFieldName(Callback successCallback) {
        try {
            successCallback.invoke(fieldName);
        } catch (IllegalViewOperationException e) { }
    }
}