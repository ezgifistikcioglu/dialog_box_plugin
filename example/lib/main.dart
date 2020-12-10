import 'dart:typed_data';

import 'package:dialog_box/dialog_box.dart';
import 'package:dialog_box/request.dart';
import 'package:dialog_box_example/CustomButton.dart';
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

  showAlert() async {
    String response;
    try {
      AlertDialogRequest request = AlertDialogRequest(
          content: "showAlertDialog",
          subText: "Alert Dialog show a message",
          setTextColor: Colors.pink,
          toastText: "YES was clicked",
          okButtonText: "YES");
      response = await DialogBox.showAlertDialog(request);
      setState(() {
        result = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  simpleAlert() async {
    String response;
    try {
      AlertDialogRequest request = AlertDialogRequest(
          content: "Simple Alert",
          setTextColor: Colors.yellow,
          subTextColor: Colors.blueGrey,
          subText: "Simple Alert Dialog show a message",
          okButtonText: "K",
          toastText: "K was clicked");
      response = await DialogBox.simpleAlert(request);
      setState(() {
        result = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  withItems() async {
    String response;
    try {
      AlertDialogRequest request = AlertDialogRequest(
          content: "with Items Alert",
          okButtonText: "K",
          noButtonText: "Dot",
          toastText: "YES was clicked",
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
          okButtonText: "K",
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
        okButtonText: "K",
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
        okButtonText: "ADD",
        okButtonColor: Colors.purple,
        setTextColor: Colors.yellow,
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
        okButtonText: "K",
        okButtonColor: Colors.purple,
        setTextColor: Colors.yellow,
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
          okButtonColor: Colors.purple,
          setTextColor: Colors.yellow,
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
                CustomButton(text: "Show Alert Dialog", onPressed: showAlert),
                CustomButton(text: "Show simpleAlert", onPressed: simpleAlert),
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
