package com.verygoodsecurity.reactnative;

import com.facebook.react.ReactActivity;
import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import android.os.Bundle;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import android.util.Log;
import com.verygoodsecurity.reactnative.collect.scanner.ScanModule;
import java.util.List;
import java.util.ArrayList;
import com.facebook.react.ReactPackage;
import java.util.Arrays;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.verygoodsecurity.reactnative.collect.VGSCollectModule;
import com.verygoodsecurity.reactnative.collect.VGSCollectPackage;

public class MainActivity extends ReactActivity {
//"VGS React Native Demo"
  @Override
  protected String getMainComponentName() {
    return "Awesome_Project";
  }

  @Override
  public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    ReactInstanceManager m = getReactInstanceManager();
    List<ReactPackage> l = m.getPackages();
    for(int i = 0;i<l.size();i++) {
      ReactPackage rp = l.get(i);
      if(rp instanceof VGSCollectPackage) {
        VGSCollectModule module = ((VGSCollectPackage)rp).getVGSCollectModule();
        module.onActivityResult(requestCode, resultCode, data);
      }
    }
  }
}
