package com.verygoodsecurity.reactnative.show;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import android.app.Activity;
import android.content.Intent;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.verygoodsecurity.vgsshow.core.logs.VGSShowLogger;
import com.verygoodsecurity.vgsshow.VGSShow;
import com.verygoodsecurity.vgsshow.core.VGSEnvironment;
import com.verygoodsecurity.vgsshow.core.listener.VGSOnResponseListener;
import com.verygoodsecurity.vgsshow.core.network.model.VGSResponse;
import com.verygoodsecurity.vgsshow.core.network.client.VGSHttpMethod;
import com.verygoodsecurity.vgsshow.widget.core.VGSView;
import android.util.Log;

public class VGSShowModule extends ReactContextBaseJavaModule {

    private static final String RESPONSE_EVENT_NAME = "VGSShowOnVGSResponse";
    private static final String VAULT_ID = "tntpszqgikn";
    private static final String MODULE_NAME = "VGSShow";
    private static final String PATH = "/post";

    private static ReactApplicationContext reactContext;
    private VGSShow show;

    VGSShowModule(ReactApplicationContext c) {
        super(c);
        reactContext = c;
    }

    @Override
    public void initialize() {
        super.initialize();
        VGSShowLogger.INSTANCE.setLevel(VGSShowLogger.Level.DEBUG);

        Activity activity = reactContext.getCurrentActivity();


        show = new VGSShow.Builder(activity, VAULT_ID)
            .setEnvironment(new VGSEnvironment.Sandbox())
            .build();

        initListeners();
    }
//
//    @ReactMethod
//    public void init() {
//
//    }

    public void bindView(VGSView view) {
        show.subscribe(view);
    }

    private void initListeners() {
        show.addOnResponseListener(new VGSOnResponseListener() {
            @Override
            public void onResponse( VGSResponse response) {
//                sendResponse(response);
            }
        });
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void submitAsync() {
        Log.e("test", "submitAsync");
//        Map<String, Object> payload = new HashMap<>();
//
//        show.requestAsync(PATH, VGSHttpMethod.POST, map);
    }


//    private void sendResponse(VGSResponse response) {
//        String responseStr = " ";
//
//        if(response instanceof VGSResponse.SuccessResponse ) {
//            parseNumberAlias(response);
//            parseDateAlias(response);
//
//            responseStr = "Code: " + ((VGSResponse.SuccessResponse) response).getSuccessCode();
//        } else if(response instanceof VGSResponse.ErrorResponse ) {
//            responseStr = new StringBuilder("Code: ")
//                    .append(response.getCode())
//                    .append(" \n ")
//                    .append(((VGSResponse.ErrorResponse) response).getLocalizeMessage())
//                    .toString();
//        }
//
//        this.getReactApplicationContext()
//                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
//                .emit(RESPONSE_EVENT_NAME, responseStr);
//
//    }
//
//    private void parseNumberAlias(VGSResponse response) {
//        String token = JSONObjectUtil.getValue(response.getBody(), "cardNumber");
//        this.getReactApplicationContext()
//                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
//                .emit("cardNumberToken", token);
//    }
//
//    private void parseDateAlias(VGSResponse response) {
//        String token = JSONObjectUtil.getValue(response.getBody(), "expDate");
//        this.getReactApplicationContext()
//                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
//                .emit("expirationDateToken", token);
//    }

}