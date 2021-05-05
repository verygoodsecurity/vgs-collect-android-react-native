package com.verygoodsecurity.reactnative.collect.field.date;

import android.view.Gravity;
import com.facebook.react.bridge.ReactMethod;
import android.util.Log;
import android.util.TypedValue;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.Callback;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.verygoodsecurity.vgscollect.widget.ExpirationDateEditText;
import com.verygoodsecurity.vgscollect.widget.VGSTextInputLayout;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.verygoodsecurity.vgscollect.view.date.DatePickerMode;
import com.verygoodsecurity.reactnative.collect.VGSCollectOnCreateViewInstanceListener;
import com.verygoodsecurity.vgscollect.view.card.FieldType;
import com.verygoodsecurity.reactnative.util.ResourceUtil;

public class CardExpDateManager extends ViewGroupManager<VGSTextInputLayout> {
    private ExpirationDateEditText editText;
    private VGSTextInputLayout vgsTextInputLayout;

    private VGSCollectOnCreateViewInstanceListener listener;

    CardExpDateManager(VGSCollectOnCreateViewInstanceListener listener) {
        super();
        this.listener = listener;
    }

    @Override
    public String getName() {
        return "CardExpDateLayout";
    }

    @Override
    protected VGSTextInputLayout createViewInstance(ThemedReactContext reactContext) {
        createVGSTextInputLayout(reactContext);
        createExpirationDateEditText(reactContext);

        return vgsTextInputLayout;
    }

    private void createVGSTextInputLayout(ThemedReactContext reactContext) {
        vgsTextInputLayout = new VGSTextInputLayout(reactContext);
        vgsTextInputLayout.setHint("Exp Date");
    }

    @ReactProp(name = "padding")
    public void setPadding(VGSTextInputLayout view, int padding) {
        int paddingDp = ResourceUtil.convertPxToDp(view.getContext(), padding);
        editText.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
    }

    @ReactProp(name = "fontSize")
    public void setFontSize(VGSTextInputLayout view, int size) {
        editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
    }

    @ReactProp(name = "hint")
    public void setHint(VGSTextInputLayout view, String text) {
        view.setHint(text);
    }

    @ReactProp(name = "fiendName")
    public void setFieldName(VGSTextInputLayout view, String text) {
        editText.setFieldName(text);
    }

    @ReactProp(name = "corners", defaultInt = 0)
    public void setBoxCornerRadius(VGSTextInputLayout view, int radius) {
        view.setBoxCornerRadius(radius, radius, radius, radius);
    }

    private void createExpirationDateEditText(ThemedReactContext reactContext) {
        editText = new ExpirationDateEditText(reactContext);
        editText.setIsRequired(true);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        editText.setDateRegex("MM/yy");
        editText.setDatePickerMode(DatePickerMode.SPINNER);

        editText.setText("12/37");

        vgsTextInputLayout.addView(editText);

        listener.onCreateViewInstance(editText);
    }

    public ExpirationDateEditText getEditTextInstance() { // <-- returns the View instance
        return editText;
    }

    public String getFieldName() {
        if(editText == null) {
            return "";
        } else {
            return editText.getFieldName();
        }
    }

}