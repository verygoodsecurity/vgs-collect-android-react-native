package com.verygoodsecurity.reactnative.fields.date;

import android.net.Uri;
import android.widget.Toast;
import android.view.Gravity;
import com.facebook.react.bridge.ReactMethod;
import android.util.Log;
import android.widget.Toast;
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
import com.verygoodsecurity.reactnative.fields.OnCreateViewInstanceListener;
import com.verygoodsecurity.vgscollect.view.card.FieldType;

public class CardExpDateManager extends ViewGroupManager<VGSTextInputLayout> {
    public static final String FIELD_NAME = "expDate";
    private ExpirationDateEditText editText;
    private VGSTextInputLayout vgsTextInputLayout;

    private OnCreateViewInstanceListener listener;

    CardExpDateManager(OnCreateViewInstanceListener listener) {
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

    private void createExpirationDateEditText(ThemedReactContext reactContext) {
        editText = new ExpirationDateEditText(reactContext);
        editText.setIsRequired(true);
        editText.setFieldName(FIELD_NAME);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        editText.setDateRegex("MM/yy");
        editText.setDatePickerMode(DatePickerMode.SPINNER);

        vgsTextInputLayout.addView(editText);

        listener.onCreateViewInstance(editText);
    }

    @ReactProp(name="url")
    public void setTestPath(VGSTextInputLayout field, String urlPath) {
        android.widget.Toast.makeText(field.getContext(), urlPath, Toast.LENGTH_SHORT).show();
    }

    public ExpirationDateEditText getEditTextInstance() { // <-- returns the View instance
        return editText;
    }

    public String getFieldName() { // <-- returns the View instance
        return FIELD_NAME;
    }
}