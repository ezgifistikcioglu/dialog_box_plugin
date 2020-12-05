import 'dart:convert' show json;

import 'package:flutter/material.dart' show Color, Colors, required;

class AlertDialogRequest {
  String content;
  String subText;

  AlertDialogRequest({
    @required this.content,
    this.subText,
  });

  factory AlertDialogRequest.fromJson(String str) =>
      AlertDialogRequest.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory AlertDialogRequest.fromMap(Map<String, dynamic> json) =>
      AlertDialogRequest(
        content: json["content"] == null ? null : json["content"],
        subText: json["subText"] == null ? null : json["subText"],
      );

  Map<String, dynamic> toMap() => {
        'content': content,
        'subText': subText,
      };

  @override
  bool operator ==(Object o) {
    if (identical(this, o)) return true;
    if (runtimeType != o.runtimeType) return false;
    final AlertDialogRequest check = o;
    return o is AlertDialogRequest &&
        check.content == content &&
        check.subText == subText;
  }

  @override
  // TODO: implement hashCode
  int get hashCode => super.hashCode;
}
