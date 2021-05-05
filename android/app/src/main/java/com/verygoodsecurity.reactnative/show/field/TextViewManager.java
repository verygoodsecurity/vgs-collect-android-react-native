package com.verygoodsecurity.reactnative.show.field;

import android.util.TypedValue;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ThemedReactContext;

import com.verygoodsecurity.reactnative.show.VGSShowOnCreateViewInstanceListener;

import com.verygoodsecurity.vgsshow.widget.VGSTextView;

import com.verygoodsecurity.reactnative.util.ResourceUtil;

public class TextViewManager extends ViewGroupManager<VGSTextView> {

    private VGSTextView textView;

    private VGSShowOnCreateViewInstanceListener listener;

    TextViewManager(VGSShowOnCreateViewInstanceListener listener) {
        super();
        this.listener = listener;
    }

    @Override
    public String getName() {
        return "VGSTextView";
    }

    @ReactProp(name = "contentPath")
    public void setContentPath(VGSTextView view, String text) {
        view.setContentPath(text);
    }

    @ReactProp(name = "padding")
    public void setPadding(VGSTextView view, int padding) {
        int paddingDp = ResourceUtil.convertPxToDp(view.getContext(), padding);
        view.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
    }

    @ReactProp(name = "fontSize")
    public void setFontSize(VGSTextView view, int size) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
    }

    @ReactProp(name = "hint")
    public void setHint(VGSTextView view, String text) {
        view.setHint(text);
    }

    @Override
    protected VGSTextView createViewInstance(ThemedReactContext reactContext) {
        textView = new VGSTextView(reactContext);

        return textView;
    }

    public String getContentPath() {
        if(textView == null) {
            return "";
        } else {
            return textView.getContentPath();
        }
    }
}