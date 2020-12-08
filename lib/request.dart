import 'dart:convert' show json;
import 'dart:ui' show hashValues;

import 'package:flutter/material.dart' show Color, Colors, required;

class AlertDialogRequest {
  String content;
  String subText;
  int textSize;
  int messageTextSize;
  String okButtonText;
  String noButtonText;
  String neutralButtonText;
  Color backgroundColor;
  Color setTextColor;
  Color setNoTextColor;
  Color subTextColor;
  Color okButtonColor;
  Color noButtonColor;
  Color neutralButtonColor;
  Color setNeutralTextColor;

  String toastText = " Please write a toast message!";
  List<String> itemList = ["Apple", "Banana", "Orange", "Grapes"];

  AlertDialogRequest({
    @required this.content,
    this.subText,
    this.textSize = 20,
    this.messageTextSize = 17,
    this.okButtonText = "Ok",
    this.noButtonText = "Cancel",
    this.neutralButtonText = "Neutral",
    this.toastText,
    this.itemList,
    this.backgroundColor = Colors.purple,
    this.setTextColor = Colors.white,
    this.setNoTextColor = Colors.pink,
    this.subTextColor = Colors.white,
    this.okButtonColor = Colors.blue,
    this.noButtonColor = Colors.orangeAccent,
    this.neutralButtonColor = Colors.deepOrange,
    this.setNeutralTextColor = Colors.yellow,
  });

  factory AlertDialogRequest.fromJson(String str) =>
      AlertDialogRequest.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory AlertDialogRequest.fromMap(Map<String, dynamic> json) =>
      AlertDialogRequest(
        content: json["content"] == null ? null : json["content"],
        subText: json["subText"] == null ? null : json["subText"],
        textSize: json["textSize"] == null ? null : json["textSize"],
        messageTextSize:
            json["messageTextSize"] == null ? null : json["messageTextSize"],
        okButtonText:
            json["okButtonText"] == null ? null : json["okButtonText"],
        noButtonText:
            json["noButtonText"] == null ? null : json["noButtonText"],
        neutralButtonText: json["neutralButtonText"] == null
            ? null
            : json["neutralButtonText"],
        toastText: json["toastText"] == null ? null : json["toastText"],
        itemList: json["itemList"] == null ? null : json["itemList"],
        backgroundColor:
            json["backgroundColor"] == null ? null : json["backgroundColor"],
        setTextColor:
            json["setTextColor"] == null ? null : json["setTextColor"],
        setNoTextColor:
            json["setNoTextColor"] == null ? null : json["setNoTextColor"],
        subTextColor:
            json["subTextColor"] == null ? null : json["subTextColor"],
        okButtonColor:
            json["okButtonColor"] == null ? null : json["okButtonColor"],
        noButtonColor:
            json["noButtonColor"] == null ? null : json["noButtonColor"],
        neutralButtonColor: json["neutralButtonColor"] == null
            ? null
            : json["neutralButtonColor"],
        setNeutralTextColor: json["setNeutralTextColor"] == null
            ? null
            : json["setNeutralTextColor"],
      );

  Map<String, dynamic> toMap() => {
        'content': content,
        'subText': subText,
        'textSize': textSize,
        'messageTextSize': messageTextSize,
        'okButtonText': okButtonText,
        'noButtonText': noButtonText,
        'neutralButtonText': neutralButtonText,
        'toastText': toastText,
        'itemList': itemList,
        'backgroundColor': backgroundColor.value,
        'setTextColor': setTextColor.value,
        'setNoTextColor': setNoTextColor.value,
        'subTextColor': subTextColor.value,
        'okButtonColor': okButtonColor.value,
        'noButtonColor': noButtonColor.value,
        'neutralButtonColor': neutralButtonColor.value,
        'setNeutralTextColor': setNeutralTextColor.value,
      };

  @override
  bool operator ==(Object o) {
    if (identical(this, o)) return true;
    if (runtimeType != o.runtimeType) return false;
    final AlertDialogRequest check = o;
    return o is AlertDialogRequest &&
        check.content == content &&
        check.subText == subText &&
        check.textSize == textSize &&
        check.okButtonText == okButtonText &&
        check.noButtonText == noButtonText &&
        check.toastText == toastText &&
        check.neutralButtonText == neutralButtonText &&
        check.itemList == itemList &&
        check.backgroundColor == backgroundColor &&
        check.setTextColor == setTextColor &&
        check.subTextColor == subTextColor &&
        check.messageTextSize == messageTextSize &&
        check.okButtonColor == okButtonColor &&
        check.setNoTextColor == setNoTextColor &&
        check.noButtonColor == noButtonColor &&
        check.neutralButtonColor == neutralButtonColor &&
        check.setNeutralTextColor == setNeutralTextColor;
  }

  @override
  int get hashCode => hashValues(
        content,
        subText,
        textSize,
        setNoTextColor,
        messageTextSize,
        okButtonText,
        noButtonText,
        toastText,
        itemList,
        backgroundColor,
        setTextColor,
        subTextColor,
        okButtonColor,
        noButtonColor,
        neutralButtonText,
        neutralButtonColor,
        setNeutralTextColor,
      );
}
