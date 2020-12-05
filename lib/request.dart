import 'dart:convert' show json;
import 'dart:ui' show hashValues;

import 'package:flutter/material.dart' show Color, Colors, required;

class AlertDialogRequest {
  String content;
  String subText;
  String okButtonText;
  String noButtonText;
  Color backgroundColor;
  Color setTextColor;
  String toastText = " Please write a toast message!";
  List<String> itemList = ["Apple", "Banana", "Orange", "Grapes"];

  AlertDialogRequest({
    @required this.content,
    this.subText,
    this.okButtonText = "Ok",
    this.noButtonText = "Cancel",
    this.toastText,
    this.itemList,
    this.backgroundColor = Colors.purple,
    this.setTextColor = Colors.white,
  });

  factory AlertDialogRequest.fromJson(String str) =>
      AlertDialogRequest.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory AlertDialogRequest.fromMap(Map<String, dynamic> json) =>
      AlertDialogRequest(
        content: json["content"] == null ? null : json["content"],
        subText: json["subText"] == null ? null : json["subText"],
        okButtonText:
            json["okButtonText"] == null ? null : json["okButtonText"],
        noButtonText:
            json["noButtonText"] == null ? null : json["noButtonText"],
        toastText: json["toastText"] == null ? null : json["toastText"],
        itemList: json["itemList"] == null ? null : json["itemList"],
        backgroundColor:
            json["backgroundColor"] == null ? null : json["backgroundColor"],
        setTextColor:
            json["setTextColor"] == null ? null : json["setTextColor"],
      );

  Map<String, dynamic> toMap() => {
        'content': content,
        'subText': subText,
        'okButtonText': okButtonText,
        'noButtonText': noButtonText,
        'toastText': toastText,
        'itemList': itemList,
        'backgroundColor': backgroundColor.value,
        'setTextColor': setTextColor.value,
      };

  @override
  bool operator ==(Object o) {
    if (identical(this, o)) return true;
    if (runtimeType != o.runtimeType) return false;
    final AlertDialogRequest check = o;
    return o is AlertDialogRequest &&
        check.content == content &&
        check.subText == subText &&
        check.okButtonText == okButtonText &&
        check.noButtonText == noButtonText &&
        check.toastText == toastText &&
        check.itemList == itemList &&
        check.backgroundColor == backgroundColor &&
        check.setTextColor == setTextColor;
  }

  @override
  int get hashCode => hashValues(
        content,
        subText,
        okButtonText,
        noButtonText,
        toastText,
        itemList,
        backgroundColor,
        setTextColor,
      );
}
