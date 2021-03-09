package com.awesome_project.collect;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import android.app.Activity;
import android.util.Log;
import android.content.Intent;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import android.app.Activity;
import com.verygoodsecurity.vgscollect.core.Environment;
import com.verygoodsecurity.vgscollect.core.VGSCollect;
import com.verygoodsecurity.vgscollect.core.HTTPMethod;
import com.awesome_project.fields.OnCreateViewInstanceListener;
import com.verygoodsecurity.vgscollect.view.InputFieldView;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.verygoodsecurity.vgscollect.core.VgsCollectResponseListener;
import com.verygoodsecurity.vgscollect.core.model.network.VGSResponse;
import com.verygoodsecurity.vgscollect.core.model.state.FieldState;
import com.verygoodsecurity.vgscollect.core.storage.OnFieldStateChangeListener;

public class VGSCollectModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;
    private Activity activity;

    private VGSCollect collect;

    VGSCollectModule(ReactApplicationContext c) {
        super(c);
        reactContext = c;
    }

    @Override
    public void initialize() {
        super.initialize();
        activity = reactContext.getCurrentActivity();

        init();
    }

    @ReactMethod
    public void init() {
        collect = new VGSCollect.Builder(activity, "<vault_id>")
                .setEnvironment(Environment.SANDBOX)
                .create();

        initListeners();
        addStaticHeaders();
    }

    private void initListeners() {
        collect.addOnResponseListeners(new VgsCollectResponseListener() {
            @Override
            public void onResponse( VGSResponse response) {
                sendResponse(response);
            }
        });
        collect.addOnFieldStateChangeListener(new OnFieldStateChangeListener() {
            @Override
            public void onStateChange(FieldState state) {
                updateUserStates();
            }
        });
    }

    private void addStaticHeaders() {
        HashMap data = new HashMap<String, String>();
        data.put("nonSDKValue", "some additional data");
        collect.setCustomData(data);
    }

    @Override
    public String getName() {
        return "VGSCollect";
    }

    @ReactMethod
    public void submitAsync() {
        collect.asyncSubmit("/post", HTTPMethod.POST);
    }

    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        collect.onActivityResult(requestCode, resultCode, data);
    }

    public void bindView(InputFieldView inputFieldView) {
        collect.bindView(inputFieldView);
    }

    private void sendResponse(VGSResponse response) {
        String eventName = "onVGSResponse";
        String responseStr = "onVGSResponse";

        if(response instanceof VGSResponse.SuccessResponse ) {
            responseStr = "Code: " + ((VGSResponse.SuccessResponse) response).getSuccessCode();;
        } else {
            responseStr = "Code: " + response.toString();
        }

        this.getReactApplicationContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, responseStr);
    }

    private void updateUserStates() {
        String eventName = "onVGSStateChange";
        List<FieldState> states = collect.getAllStates();
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i<states.size();i++) {
            FieldState it = states.get(i);
            builder.append(it.getFieldName()).append("\n")
                    .append("   hasFocus: ").append(it.getHasFocus()).append("\n")
                    .append("   isValid: ").append(it.isValid()).append("\n")
                    .append("   isEmpty: ").append(it.isEmpty()).append("\n")
                    .append("   isRequired: ").append(it.isRequired()).append("\n");
            if (it instanceof FieldState.CardNumberState) {
                builder.append("    type: ").append(((FieldState.CardNumberState) it).getCardBrand()).append("\n")
                        .append("       end: ").append(((FieldState.CardNumberState) it).getLast()).append("\n")
                        .append("       bin: ").append(((FieldState.CardNumberState) it).getBin()).append("\n")
                        .append(((FieldState.CardNumberState) it).getNumber()).append("\n");
            }

            builder.append("\n");
        }
        this.getReactApplicationContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, builder.toString());
    }

}