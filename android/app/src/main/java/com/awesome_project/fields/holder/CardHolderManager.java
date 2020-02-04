package com.awesome_project.fields.holder;

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
import com.verygoodsecurity.vgscollect.widget.VGSCardNumberEditText;
import com.verygoodsecurity.vgscollect.widget.VGSEditText;
import com.verygoodsecurity.vgscollect.widget.VGSTextInputLayout;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.verygoodsecurity.vgscollect.widget.VGSEditText;
import com.awesome_project.fields.OnCreateViewInstanceListener;
import com.verygoodsecurity.vgscollect.view.card.FieldType;

public class CardHolderManager extends ViewGroupManager<VGSTextInputLayout> {
    public static final String FIELD_NAME = "cardHolder";
    private VGSEditText editText;
    private VGSTextInputLayout vgsTextInputLayout;

    private OnCreateViewInstanceListener listener;

    CardHolderManager(OnCreateViewInstanceListener listener) {
        super();
        this.listener = listener;
    }

    @Override
    public String getName() {
        return "CardHolderLayout";
    }

    @Override
    protected VGSTextInputLayout createViewInstance(ThemedReactContext reactContext) {

        vgsTextInputLayout = new VGSTextInputLayout(reactContext);
        vgsTextInputLayout.setHint("card holder");

        editText = new VGSEditText(reactContext);
        editText.setFieldName(FIELD_NAME);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        editText.setFieldType(FieldType.CARD_HOLDER_NAME);

        vgsTextInputLayout.addView(editText);

        listener.onCreateViewInstance(editText);

        return vgsTextInputLayout;
    }

    @ReactProp(name="url")
    public void setTestPath(VGSTextInputLayout field, String urlPath) {
        android.widget.Toast.makeText(field.getContext(), urlPath, Toast.LENGTH_SHORT).show();
    }

    public VGSEditText getEditTextInstance() { // <-- returns the View instance
        return editText;
    }

    public String getFieldName() { // <-- returns the View instance
        return FIELD_NAME;
    }
}