import 'dart:async';

import 'package:dialog_box/request.dart';
import 'package:flutter/services.dart';

class DialogBox {
  static const MethodChannel _channel = const MethodChannel('dialog_box');

  static Future<String> withPositiveButton(AlertDialogRequest request) async {
    final String alert =
        await _channel.invokeMethod('withPositiveButton', request.toMap());
    return alert;
  }

  static Future<String> withPositiveAndNegativeButton(
      AlertDialogRequest request) async {
    final String simpleAlert = await _channel.invokeMethod(
        'withPositiveAndNegativeButton', request.toMap());
    return simpleAlert;
  }

  static Future<String> withItems(AlertDialogRequest request) async {
    final String withItems =
        await _channel.invokeMethod('withItems', request.toMap());
    return withItems;
  }

  static Future<String> withMultiChoiceItems(AlertDialogRequest request) async {
    final String withMultiChoiceItems =
        await _channel.invokeMethod('withMultiChoiceItems', request.toMap());
    return withMultiChoiceItems;
  }

  static Future<String> withEditText(AlertDialogRequest request) async {
    final String withEditText =
        await _channel.invokeMethod('withEditText', request.toMap());
    return withEditText;
  }

  static Future<String> withImageView(AlertDialogRequest request) async {
    final String withImageView =
        await _channel.invokeMethod('withImageView', request.toMap());
    return withImageView;
  }

  static Future<String> withSeekBar(AlertDialogRequest request) async {
    final String withSeekBar =
        await _channel.invokeMethod('withSeekBar', request.toMap());
    return withSeekBar;
  }

  static Future<String> withRatingBar(AlertDialogRequest request) async {
    final String withRatingBar =
        await _channel.invokeMethod('withRatingBar', request.toMap());
    return withRatingBar;
  }
}
