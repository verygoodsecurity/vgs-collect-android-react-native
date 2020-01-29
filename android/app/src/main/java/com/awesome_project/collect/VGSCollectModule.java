package com.awesome_project.collect;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import com.verygoodsecurity.vgscollect.core.Environment;
import com.verygoodsecurity.vgscollect.core.VGSCollect;
import com.verygoodsecurity.vgscollect.core.HTTPMethod;
import com.awesome_project.number.OnCreateViewInstanceListener;
import com.verygoodsecurity.vgscollect.view.InputFieldView;

public class VGSCollectModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;

    private VGSCollect collect;

    VGSCollectModule(ReactApplicationContext c) {
        super(c);
        reactContext = c;

        collect = new VGSCollect("tntpszqgikn", Environment.SANDBOX);
    }

    @Override
    public String getName() {
        return "VGSCollect";
    }

    @ReactMethod
    public void submitAsync() {
        Activity activity = this.getReactApplicationContext().getCurrentActivity();

        android.widget.Toast.makeText(reactContext, "submit data", Toast.LENGTH_SHORT).show();

        collect.resetCustomData();
        HashMap data = new HashMap<String, String>();
        data.put("nonSDKValue", "some additional data");
        collect.setCustomData(data);

        collect.asyncSubmit(activity, "/post", HTTPMethod.POST);
    }

    public void bindView(InputFieldView inputFieldView) {
        android.widget.Toast.makeText(reactContext, "bindView", Toast.LENGTH_SHORT).show();

        collect.bindView(inputFieldView);
    }

}