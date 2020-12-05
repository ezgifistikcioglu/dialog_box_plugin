import 'dart:async';

import 'package:dialog_box/dialog_box.dart';
import 'package:dialog_box/request.dart';
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
  String _platformVersion = 'Unknown';
  String rslt;
  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = await DialogBox.platformVersion;
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  showAlert() async {
    String response;
    try {
      AlertDialogRequest request =
          AlertDialogRequest(content: "text", subText: "hi baby");
      response = await DialogBox.showAlertDialog(request);
      setState(() {
        rslt = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  simpleAlert() async {
    String response;
    try {
      response = await DialogBox.simpleAlert();
      setState(() {
        rslt = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  withItems() async {
    String response;
    try {
      response = await DialogBox.withItems();
      setState(() {
        rslt = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  withMultiChoiceItems() async {
    String response;
    try {
      response = await DialogBox.withMultiChoiceItems();
      setState(() {
        rslt = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  withEditText() async {
    String response;
    try {
      response = await DialogBox.withEditText();
      setState(() {
        rslt = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  withImageView() async {
    String response;
    try {
      response = await DialogBox.withImageView();
      setState(() {
        rslt = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  withSeekBar() async {
    String response;
    try {
      response = await DialogBox.withSeekBar();
      setState(() {
        rslt = response;
      });
    } on PlatformException {
      response = 'Failed to get platform version.';
    }
  }

  withRatingBar() async {
    String response;
    try {
      response = await DialogBox.withRatingBar();
      setState(() {
        rslt = response;
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
          title: const Text('Plugin example app'),
        ),
        body: Column(
          children: [
            MaterialButton(
              onPressed: showAlert,
              child: Text(
                "Show Alert Dialog",
                style: TextStyle(color: Colors.purple),
              ),
              color: Colors.blue,
            ),
            MaterialButton(
              onPressed: simpleAlert,
              child: Text(
                "Show simpleAlert",
                style: TextStyle(color: Colors.purple),
              ),
              color: Colors.blue,
            ),
            MaterialButton(
              onPressed: withItems,
              child: Text(
                "Show withItems",
                style: TextStyle(color: Colors.purple),
              ),
              color: Colors.blue,
            ),
            MaterialButton(
              onPressed: withMultiChoiceItems,
              child: Text(
                "Show withMultiChoiceItems",
                style: TextStyle(color: Colors.purple),
              ),
              color: Colors.blue,
            ),
            MaterialButton(
              onPressed: withEditText,
              child: Text(
                "Show withEditText",
                style: TextStyle(color: Colors.purple),
              ),
              color: Colors.blue,
            ),
            MaterialButton(
              onPressed: withImageView,
              child: Text(
                "Show withImageView",
                style: TextStyle(color: Colors.purple),
              ),
              color: Colors.blue,
            ),
            MaterialButton(
              onPressed: withSeekBar,
              child: Text(
                "Show withSeekBar",
                style: TextStyle(color: Colors.purple),
              ),
              color: Colors.blue,
            ),
            MaterialButton(
              onPressed: withRatingBar,
              child: Text(
                "Show withRatingBar",
                style: TextStyle(color: Colors.purple),
              ),
              color: Colors.blue,
            ),
          ],
        ),
      ),
    );
  }
}
