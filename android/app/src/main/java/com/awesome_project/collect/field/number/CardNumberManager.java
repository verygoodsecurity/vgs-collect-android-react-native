package com.verygoodsecurity.reactnative.collect.field.number;

import android.net.Uri;
import android.widget.Toast;
import android.view.Gravity;
import com.facebook.react.bridge.ReactMethod;
import android.util.Log;
import android.widget.Toast;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.Callback;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.verygoodsecurity.vgscollect.widget.VGSCardNumberEditText;
import com.verygoodsecurity.vgscollect.widget.VGSTextInputLayout;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ViewGroupManager;
import android.util.TypedValue;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.verygoodsecurity.reactnative.collect.OnCreateViewInstanceListener;
import com.verygoodsecurity.vgscollect.view.card.FieldType;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReadableArray;

public class CardNumberManager extends ViewGroupManager<VGSTextInputLayout> {
    public static final String FIELD_NAME = "cardNumber";
    private VGSCardNumberEditText editText;
    private VGSTextInputLayout vgsTextInputLayout;

    private OnCreateViewInstanceListener listener;

    CardNumberManager(OnCreateViewInstanceListener listener) {
        super();
        this.listener = listener;
    }

    @Override
    public String getName() {
        return "CardNumberLayout";
    }

    @Override
    protected VGSTextInputLayout createViewInstance(ThemedReactContext reactContext) {
        createVGSTextInputLayout(reactContext);
        createVGSCardNumberEditText(reactContext);

        return vgsTextInputLayout;
    }

    private void createVGSTextInputLayout(ThemedReactContext reactContext) {
        vgsTextInputLayout = new VGSTextInputLayout(reactContext);
    }

    @ReactProp(name = "hint")
    public void setHint(VGSTextInputLayout view, String text) {
        Log.e("TAG", "text: " + text);
        view.setHint(text);
    }

    @ReactProp(name = "corners", defaultInt = 0)
    public void setBoxCornerRadius(VGSTextInputLayout view, int radius) {
        view.setBoxCornerRadius(radius, radius, radius, radius);
    }

    private void createVGSCardNumberEditText(ThemedReactContext reactContext) {
        editText = new VGSCardNumberEditText(reactContext);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        editText.setIsRequired(true);
        editText.setDivider('-');
        editText.setCardBrandIconGravity(Gravity.END);
        editText.setFieldName(FIELD_NAME);

        vgsTextInputLayout.addView(editText);

        listener.onCreateViewInstance(editText);
    }

    public VGSCardNumberEditText getEditTextInstance() { // <-- returns the View instance
        return editText;
    }

    public String getFieldName() { // <-- returns the View instance
        return FIELD_NAME;
    }
}