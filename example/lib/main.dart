import 'dart:typed_data';

import 'package:dialog_box/dialogBox.dart';
import 'package:dialog_box/request.dart';
import 'package:dialog_box/utils/Gravity.dart';
import 'package:dialog_box_example/view/CustomButton.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String result;
  @override
  void initState() {
    super.initState();
  }

  withPositiveButton() async {
    String response;
    try {
      AlertDialogRequest request = AlertDialogRequest(
          content: "With Positive Button",
          subText: "Alert Dialog show a message",
          gravity: Gravity.Center,
          okButtonTextColor: Colors.green,
          toastText: "YES was clicked",
          okButtonText: "YES");
      response = await DialogBox.withPositiveButton(request);
      setState(() {
        result = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  withPositiveAndNegativeButton() async {
    String response;
    try {
      AlertDialogRequest request = AlertDialogRequest(
          content: "With Positive And Negative Button",
          setTextColor: Colors.green,
          subText: "Simple Alert Dialog show a message",
          okButtonText: "OK",
          toastText: "OK was clicked");
      response = await DialogBox.withPositiveAndNegativeButton(request);
      setState(() {
        result = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  withItems() async {
    Uint8List data =
        (await rootBundle.load('assets/clipboard.png')).buffer.asUint8List();
    String response;
    try {
      AlertDialogRequest request = AlertDialogRequest(
          content: "with Items Alert",
          data: data,
          iconHeight: 100,
          iconWidth: 100,
          okButtonText: "OK",
          noButtonText: "NO",
          toastText: "OK was clicked",
          itemList: ["a", "b", "c"]);
      response = await DialogBox.withItems(request);
      setState(() {
        result = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  withMultiChoiceItems() async {
    String response;
    try {
      AlertDialogRequest request = AlertDialogRequest(
          content: "This is list choice dialog box",
          setTextColor: Colors.yellow,
          okButtonText: "OK",
          gravity: Gravity.Center,
          itemList: ["a", "b", "c"]);
      response = await DialogBox.withMultiChoiceItems(request);
      setState(() {
        result = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  withEditText() async {
    String response;
    try {
      AlertDialogRequest request = AlertDialogRequest(
        content: "With Edit Text",
        okButtonText: "OK",
        gravity: Gravity.Center,
        setTextColor: Colors.yellow,
        textSize: 25,
      );
      response = await DialogBox.withEditText(request);
      setState(() {
        result = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  withImageView() async {
    Uint8List data =
        (await rootBundle.load('assets/test.jpg')).buffer.asUint8List();
    String response;
    try {
      AlertDialogRequest request = AlertDialogRequest(
        content: "with Image View",
        data: data,
        gravity: Gravity.Center,
        okButtonText: "ADD",
        textSize: 25,
      );
      response = await DialogBox.withImageView(request);
      setState(() {
        result = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  withSeekBar() async {
    String response;
    try {
      AlertDialogRequest request = AlertDialogRequest(
        content: "With SeekBar",
        gravity: Gravity.CenterHorizontal,
        okButtonText: "OK",
        textSize: 25,
      );
      response = await DialogBox.withSeekBar(request);
      setState(() {
        result = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  withRatingBar() async {
    String response;
    try {
      AlertDialogRequest request = AlertDialogRequest(
          content: "With RatingBar",
          okButtonText: "SHOW",
          gravity: Gravity.CenterHorizontal,
          textSize: 25);
      response = await DialogBox.withRatingBar(request);
      setState(() {
        result = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            backgroundColor: Colors.purple[200],
            title: const Text('Alert Dialog Lists'),
          ),
          body: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                SizedBox(
                  height: 12.0,
                ),
                CustomButton(
                    text: "Show Alert Dialog", onPressed: withPositiveButton),
                CustomButton(
                    text: "Show simpleAlert",
                    onPressed: withPositiveAndNegativeButton),
                CustomButton(
                  text: "Show withItems",
                  onPressed: withItems,
                ),
                CustomButton(
                  text: "Show withMultiChoiceItems",
                  onPressed: withMultiChoiceItems,
                ),
                CustomButton(
                  text: "Show withEditText",
                  onPressed: withEditText,
                ),
                CustomButton(
                  text: "Show withImageView",
                  onPressed: withImageView,
                ),
                CustomButton(
                  text: "Show withSeekBar",
                  onPressed: withSeekBar,
                ),
                CustomButton(
                  text: "Show withRatingBar",
                  onPressed: withRatingBar,
                ),
              ],
            ),
          )),
    );
  }
}
