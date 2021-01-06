package com.huawei.hms.flutter.dialog_box;

import android.app.Activity;
import android.app.Dialog;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;

/** DialogBoxPlugin */
public class DialogBoxPlugin implements FlutterPlugin, ActivityAware {

  private Activity activity;
  private FlutterPluginBinding flutterPluginBinding;
  private MethodChannel channel;
  private DialogBoxMethodCallHandler mDialogBoxMethodCallHandler;

  private void initChannels(final BinaryMessenger messenger) {
    //init channels
    channel = new MethodChannel(messenger, "dialog_box");
  }

  private void initHandlers() {
    mDialogBoxMethodCallHandler = new DialogBoxMethodCallHandler(activity);
  }

  private void setHandlers() {
    channel.setMethodCallHandler(mDialogBoxMethodCallHandler);
  }
  private void resetHandlers() {
    if (channel != null) {
      channel.setMethodCallHandler(null);
    }
  }
  private void removeHandlers() {
    mDialogBoxMethodCallHandler = null;

  }

  private void removeChannels() {
    //remove channels
    channel = null;
  }

  public static void registerWith(PluginRegistry.Registrar registrar) {
    final DialogBoxPlugin instance = new DialogBoxPlugin();
    instance.onAttachedToEngine(registrar.messenger(), registrar.activity());
  }

  private void onAttachedToEngine(@NonNull BinaryMessenger messenger, Activity activity) {
    this.activity = activity;
    initChannels(messenger);
    initHandlers();
    setHandlers();
  }

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
    this.flutterPluginBinding = binding;
  }


  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    this.flutterPluginBinding  = null;
    resetHandlers();
    removeHandlers();
    removeChannels();
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    if (flutterPluginBinding != null) {
      onAttachedToEngine(flutterPluginBinding.getBinaryMessenger(),binding.getActivity());
    }
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
  onDetachedFromActivity();
  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
  onAttachedToActivity(binding);
  }

  @Override
  public void onDetachedFromActivity() {
   resetHandlers();
   removeHandlers();
   removeChannels();
  }
}
