package com.verygoodsecurity.reactnative.show;


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
import com.verygoodsecurity.reactnative.show.VGSShowOnCreateViewInstanceListener;
import com.verygoodsecurity.vgsshow.widget.core.VGSView;

public class VGSShowPackage implements ReactPackage, VGSShowOnCreateViewInstanceListener {

  private VGSShowModule module;

  public VGSShowOnCreateViewInstanceListener getListener() {
    return this;
  }

  public VGSShowModule getVGSShowModule() {
    return module;
  }

  @Override
  public void onCreateViewInstance(VGSView inputFieldView) {
    module.bindView(inputFieldView);
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
      return Collections.emptyList();
  }

  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
    module = new VGSShowModule(reactContext);

    List<NativeModule> modules = new ArrayList<>();

    modules.add(module);

    return modules;
  }
}