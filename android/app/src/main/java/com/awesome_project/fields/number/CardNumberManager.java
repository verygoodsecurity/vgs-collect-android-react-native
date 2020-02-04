package com.awesome_project.fields.number;

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
import com.verygoodsecurity.vgscollect.widget.VGSEditText;
import com.verygoodsecurity.vgscollect.widget.VGSTextInputLayout;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ViewGroupManager;
import android.util.TypedValue;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.verygoodsecurity.vgscollect.widget.VGSEditText;
import com.awesome_project.fields.OnCreateViewInstanceListener;
import com.verygoodsecurity.vgscollect.view.card.FieldType;

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

         vgsTextInputLayout = new VGSTextInputLayout(reactContext);
        vgsTextInputLayout.setHint("card number");

         editText = new VGSCardNumberEditText(reactContext);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        editText.setIsRequired(true);
        editText.setCardBrandIconGravity(Gravity.END);
        editText.setFieldName(FIELD_NAME);
        editText.setFieldType(FieldType.CARD_NUMBER);

        vgsTextInputLayout.addView(editText);

        listener.onCreateViewInstance(editText);

        return vgsTextInputLayout;
    }

    @ReactProp(name="url")
    public void setTestPath(VGSTextInputLayout field, String urlPath) {
        android.widget.Toast.makeText(field.getContext(), urlPath, Toast.LENGTH_SHORT).show();
    }

    public VGSCardNumberEditText getEditTextInstance() { // <-- returns the View instance
        return editText;
    }

    public String getFieldName() { // <-- returns the View instance
        return FIELD_NAME;
    }
}