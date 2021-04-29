package com.verygoodsecurity.reactnative.fields.cvc;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import com.verygoodsecurity.reactnative.fields.OnCreateViewInstanceListener;

public class CardCVCPackage implements ReactPackage {

  private OnCreateViewInstanceListener listener;
  private CardCVCManager calManager;

  public CardCVCPackage(OnCreateViewInstanceListener listener) {
    this.listener = listener;
  }

  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
      if (calManager == null) {
        calManager = new CardCVCManager(listener);
      }
      return Arrays.<NativeModule>asList(
              new CardCVCModule(reactContext, calManager)
      );
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    if (calManager == null) {
      calManager = new CardCVCManager(listener);
    }
    return Arrays.<ViewManager>asList(
            calManager
    );
  }
}