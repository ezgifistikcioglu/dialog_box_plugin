import 'dart:async';

import 'package:dialog_box/request.dart';
import 'package:flutter/services.dart';

class DialogBox {
  static const MethodChannel _channel = const MethodChannel('dialog_box');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> showAlertDialog(AlertDialogRequest request) async {
    final String alert =
        await _channel.invokeMethod('showAlertDialog', request.toMap());
    return alert;
  }

  static Future<String> simpleAlert() async {
    final String simpleAlert = await _channel.invokeMethod('simpleAlert');
    return simpleAlert;
  }

  static Future<String> withItems() async {
    final String withItems = await _channel.invokeMethod('withItems');
    return withItems;
  }

  static Future<String> withMultiChoiceItems() async {
    final String withMultiChoiceItems =
        await _channel.invokeMethod('withMultiChoiceItems');
    return withMultiChoiceItems;
  }

  static Future<String> withEditText() async {
    final String withEditText = await _channel.invokeMethod('withEditText');
    return withEditText;
  }

  static Future<String> withImageView() async {
    final String withImageView = await _channel.invokeMethod('withImageView');
    return withImageView;
  }

  static Future<String> withSeekBar() async {
    final String withSeekBar = await _channel.invokeMethod('withSeekBar');
    return withSeekBar;
  }

  static Future<String> withRatingBar() async {
    final String withRatingBar = await _channel.invokeMethod('withRatingBar');
    return withRatingBar;
  }
}
