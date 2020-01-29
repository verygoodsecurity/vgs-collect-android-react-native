package com.awesome_project.number;

import android.net.Uri;
import android.widget.Toast;
import android.view.Gravity;
import com.verygoodsecurity.vgscollect.widget.VGSCardNumberEditText;
import com.verygoodsecurity.vgscollect.widget.VGSEditText;
import com.verygoodsecurity.vgscollect.widget.VGSTextInputLayout;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.verygoodsecurity.vgscollect.widget.VGSEditText;
import com.awesome_project.number.OnCreateViewInstanceListener;
import com.verygoodsecurity.vgscollect.view.card.FieldType;

public class CardNumberManager extends SimpleViewManager<VGSTextInputLayout> {

    private OnCreateViewInstanceListener listener;

    CardNumberManager(OnCreateViewInstanceListener listener) {
        this.listener = listener;
    }

    @Override
    public String getName() {
        return "VGSEditText";
    }

    @Override
    protected VGSTextInputLayout createViewInstance(ThemedReactContext reactContext) {

        VGSTextInputLayout vgsTextInputLayout = new VGSTextInputLayout(reactContext);
        vgsTextInputLayout.setHint("card number");

        VGSCardNumberEditText editText = new VGSCardNumberEditText(reactContext);
        editText.setCardBrandIconGravity(Gravity.END);
        editText.setFieldName("cardNumber");
        editText.setFieldType(FieldType.CARD_NUMBER);

        vgsTextInputLayout.addView(editText);

        listener.onCreateViewInstance(editText);

        return vgsTextInputLayout;
    }

    @ReactProp(name="url")
    public void setTestPath(VGSTextInputLayout field, String urlPath) {
        android.widget.Toast.makeText(field.getContext(), urlPath, Toast.LENGTH_SHORT).show();
    }
}