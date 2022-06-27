package com.otavioq.RNCustomAlert;

import android.graphics.Color;
import android.app.Activity;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

public class RNCustomAlertModule extends ReactContextBaseJavaModule {
  private SweetAlertDialog sweetAlertDialog;

  RNCustomAlertModule(final ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "RNCustomAlert";
  }

  @ReactMethod
  public void showAlertWithOptions(ReadableMap options, final Callback acceptCallback) {

    String type = options.hasKey("style") ? options.getString("style") : "normal";
    String title = options.hasKey("title") ? options.getString("title") : null;
    String contentText = options.hasKey("subTitle") ? options.getString("subTitle") : null;
    String confirmButtonTitle = options.hasKey("confirmButtonTitle") ? options.getString("confirmButtonTitle") : null;
    String otherButtonTitle = options.hasKey("otherButtonTitle") && options.getBoolean("showCancel") == true ? options.getString("otherButtonTitle") : null;
    String neutralButtonTitle = options.hasKey("neutralButtonTitle") ? options.getString("neutralButtonTitle") : null;
    String barColor = options.hasKey("barColor") ? options.getString("barColor") : "";
    String confirmButtonColor = options.hasKey("confirmButtonColor") ? options.getString("confirmButtonColor") : "#27ae60";
    String otherButtonColor = options.hasKey("otherButtonColor") ? options.getString("otherButtonColor") : "#d63031";
    String neutralButtonColor = options.hasKey("neutralButtonColor") ? options.getString("neutralButtonColor") : "#3498db";
    boolean canCancel = options.hasKey("cancelable") ? options.getBoolean("cancelable") : false;

    Activity activity = getCurrentActivity();

    if (activity == null) {
      acceptCallback.invoke("error");
      return;
    }

    int alertType = 0;

    switch (type) {
      default:
      case "normal":
        alertType = SweetAlertDialog.NORMAL_TYPE;
        break;
      case "error":
        alertType = SweetAlertDialog.ERROR_TYPE;
        break;
      case "success":
        alertType = SweetAlertDialog.SUCCESS_TYPE;
        break;
      case "warning":
        alertType = SweetAlertDialog.WARNING_TYPE;
        break;
      case "progress":
        alertType = SweetAlertDialog.PROGRESS_TYPE;
        break;
      case "info":
        alertType = SweetAlertDialog.INFO_TYPE;
        break;
    }

    sweetAlertDialog = new SweetAlertDialog(activity, alertType, canCancel);

    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
      @Override
      public void onClick(SweetAlertDialog sweetAlertDialog) {
        acceptCallback.invoke("confirmed");
        sweetAlertDialog.dismissWithAnimation();
      }
    });
    sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
      @Override
      public void onClick(SweetAlertDialog sweetAlertDialog) {
        acceptCallback.invoke("cancelled");
        sweetAlertDialog.cancel();
      }
    });
    sweetAlertDialog.setNeutralClickListener(new SweetAlertDialog.OnSweetClickListener() {
      @Override
      public void onClick(SweetAlertDialog sweetAlertDialog) {
        acceptCallback.invoke("neutral");
        sweetAlertDialog.dismissWithAnimation();
      }
    });

    sweetAlertDialog.setTitleText(title);
    sweetAlertDialog.setConfirmText(confirmButtonTitle);
    sweetAlertDialog.setCancelText(otherButtonTitle);
    sweetAlertDialog.setNeutralText(neutralButtonTitle);
    sweetAlertDialog.setContentText(contentText);
    sweetAlertDialog.setConfirmButtonBackgroundColor(Color.parseColor(confirmButtonColor));
    sweetAlertDialog.setCancelButtonBackgroundColor(Color.parseColor(otherButtonColor));
    sweetAlertDialog.setNeutralButtonBackgroundColor(Color.parseColor(neutralButtonColor));
    if (!barColor.equals("")) {
      setBarColor(barColor);
    }
    sweetAlertDialog.show();
  }

  @ReactMethod
  public void hideSweetAlert() {
    if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
      sweetAlertDialog.dismissWithAnimation();
    }
  }

  @ReactMethod
  public void changeAlertType(String alertType) {
    switch (alertType) {
      case "normal":
        sweetAlertDialog.changeAlertType(SweetAlertDialog.NORMAL_TYPE);
        break;
      case "error":
        sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
        break;
      case "success":
        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
        break;
      case "warning":
        sweetAlertDialog.changeAlertType(SweetAlertDialog.WARNING_TYPE);
        break;
      case "progress":
        sweetAlertDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        break;
      case "info":
        sweetAlertDialog.changeAlertType(SweetAlertDialog.INFO_TYPE);
        break;
      default:
        sweetAlertDialog.changeAlertType(SweetAlertDialog.NORMAL_TYPE);
        break;
    }
  }

  @ReactMethod
  public void showContentText(boolean isShow) {
    sweetAlertDialog.showContentText(isShow);
  }

  @ReactMethod
  public void resetCount() {
    sweetAlertDialog.getProgressHelper().resetCount();
  }

  // Get spinning status, better to use a boolean variable in JS side instead.
  @ReactMethod
  public void isSpinning(Promise promise) {
    try {
      promise.resolve(sweetAlertDialog.isShowing());
    } catch (Exception e) {
      promise.reject("SweetAlert", e);
    }
  }

  @ReactMethod
  public void spin() {
    sweetAlertDialog.getProgressHelper().spin();
  }

  @ReactMethod
  public void stopSpinning() {
    sweetAlertDialog.getProgressHelper().stopSpinning();
  }

  @ReactMethod
  public void setProgress(float number) {
    sweetAlertDialog.getProgressHelper().setProgress(number);
  }

  @ReactMethod
  public void setInstantProgress(float number) {
    sweetAlertDialog.getProgressHelper().setInstantProgress(number);
  }

  @ReactMethod
  public void setCircleRadius(int radius) {
    sweetAlertDialog.getProgressHelper().setCircleRadius(radius);
  }

  @ReactMethod
  public void setBarWidth(int barWidth) {
    sweetAlertDialog.getProgressHelper().setBarWidth(barWidth);
  }

  @ReactMethod
  public void setBarColor(String barColor) {
    sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor(barColor));
  }

  @ReactMethod
  public void setRimWidth(int rimWidth) {
    sweetAlertDialog.getProgressHelper().setRimWidth(rimWidth);
  }

  @ReactMethod
  public void setSpinSpeed(float spinSpeed) {
    sweetAlertDialog.getProgressHelper().setSpinSpeed(spinSpeed);
  }
}