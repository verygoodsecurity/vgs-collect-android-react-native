package com.verygoodsecurity.reactnative.collect.scanner;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;
import com.verygoodsecurity.api.cardio.ScanActivity;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import android.widget.Toast;
import android.app.Activity;
import android.util.Log;
import com.verygoodsecurity.reactnative.collect.OnCreateViewInstanceListener;
import com.verygoodsecurity.vgscollect.view.InputFieldView;
import java.util.Collections;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.module.annotations.ReactModule;

public class ScanPackage implements ReactPackage {

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new ScanModule(reactContext));
        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}