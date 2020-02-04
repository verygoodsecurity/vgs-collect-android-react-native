package com.awesome_project.fields.holder;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import com.awesome_project.fields.OnCreateViewInstanceListener;

public class CardHolderPackage implements ReactPackage {

  private OnCreateViewInstanceListener listener;
  private CardHolderManager calManager;

  public CardHolderPackage(OnCreateViewInstanceListener listener) {
    this.listener = listener;
  }

  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
      if (calManager == null) {
        calManager = new CardHolderManager(listener);
      }
      return Arrays.<NativeModule>asList(
              new CardHolderModule(reactContext, calManager)
      );
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    if (calManager == null) {
      calManager = new CardHolderManager(listener);
    }
    return Arrays.<ViewManager>asList(
            calManager
    );
  }
}