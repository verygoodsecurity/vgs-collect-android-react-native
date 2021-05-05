package com.verygoodsecurity.reactnative.collect.field.date;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import com.verygoodsecurity.reactnative.collect.VGSCollectOnCreateViewInstanceListener;

public class CardExpDatePackage implements ReactPackage {

  private VGSCollectOnCreateViewInstanceListener listener;
  private CardExpDateManager calManager;

  public CardExpDatePackage(VGSCollectOnCreateViewInstanceListener listener) {
    this.listener = listener;
  }

  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
      if (calManager == null) {
        calManager = new CardExpDateManager(listener);
      }
      return Arrays.<NativeModule>asList(
              new CardExpDateModule(reactContext, calManager)
      );
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    if (calManager == null) {
      calManager = new CardExpDateManager(listener);
    }
    return Arrays.<ViewManager>asList(
            calManager
    );
  }
}