package com.awesome_project.fields.number;

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
import com.verygoodsecurity.vgscollect.widget.VGSCardNumberEditText;
import com.verygoodsecurity.vgscollect.widget.VGSTextInputLayout;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import com.verygoodsecurity.vgscollect.core.Environment;
import com.verygoodsecurity.vgscollect.core.VGSCollect;
import com.verygoodsecurity.vgscollect.core.HTTPMethod;
import com.awesome_project.fields.OnCreateViewInstanceListener;
import com.verygoodsecurity.vgscollect.view.InputFieldView;

public class CardNumberModule extends ReactContextBaseJavaModule {

    private String fieldName;

    public CardNumberModule(ReactApplicationContext reactContext, CardNumberManager calManager) {
        super(reactContext);
        if (calManager != null) {
            fieldName = calManager.getFieldName();
        }
    }

    @Override
    public String getName() {
        return "NumberVGSEditText";
    }

    @ReactMethod
    public void getFieldName(Callback successCallback) {
        try {
            successCallback.invoke(fieldName);
        } catch (IllegalViewOperationException e) { }
    }
}