package com.awesome_project.collect;


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
import com.awesome_project.fields.OnCreateViewInstanceListener;
import com.verygoodsecurity.vgscollect.view.InputFieldView;

public class VGSCollectPackage implements ReactPackage, OnCreateViewInstanceListener {

  private VGSCollectModule module;

  public OnCreateViewInstanceListener getListener() {
    return this;
  }

  public VGSCollectModule getVGSCollectModule() {
    return module;
  }

  @Override
  public void onCreateViewInstance(InputFieldView inputFieldView) {
    module.bindView(inputFieldView);
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
      return Collections.emptyList();
  }

  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
    module = new VGSCollectModule(reactContext);

    List<NativeModule> modules = new ArrayList<>();

    modules.add(module);

    return modules;
  }
}