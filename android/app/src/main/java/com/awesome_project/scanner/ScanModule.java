package com.awesome_project.scanner;

import android.content.Intent;
import android.os.Bundle;

import com.verygoodsecurity.vgscollect.app.BaseTransmitActivity;

import java.util.HashMap;
import java.util.Map;
import com.verygoodsecurity.api.cardio.ScanActivity;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.shell.MainReactPackage;
import android.os.Bundle;
import android.util.Log;
import java.util.HashMap;
import com.facebook.react.bridge.Promise;

public class ScanModule extends ReactContextBaseJavaModule {

    public static final int IMAGE_PICKER_REQUEST = 467081;

    private HashMap<String, Integer> scanSettings = new HashMap();

    ScanModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ScanActivity";
    }

    @ReactMethod
    public void setItem(String key, int value) {
        scanSettings.put(key, value);
    }

    @ReactMethod
    public void clearBundle() {
        scanSettings.clear();
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("CARD_NUMBER", ScanActivity.CARD_NUMBER);
        constants.put("CARD_HOLDER", ScanActivity.CARD_HOLDER);

        return constants;
    }

    @ReactMethod
    public void startActivityForResult() {
        Bundle bndl = new Bundle();
        bndl.putSerializable(ScanActivity.SCAN_CONFIGURATION, scanSettings);

        ReactApplicationContext context = getReactApplicationContext();
        Intent intent = new Intent(context, ScanActivity.class);
        intent.putExtra(ScanActivity.SCAN_CONFIGURATION, scanSettings);
        context.startActivityForResult(intent, 0x3, bndl);
    }
}
