import 'package:flutter/material.dart';
import 'dart:async';
import 'dart:convert';
import 'package:flutter/services.dart';
import 'package:aesflutterplugin/aesflutterplugin.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String result = "";
    String secretKey="1bb6e77e874341fe";
    var encoder = new Base64Encoder();
    var secretKeyEncode = utf8.encode(secretKey);
    var secretKeyBase64 = encoder.convert(secretKeyEncode);

    Map<String, String> map = new Map();
    map.putIfAbsent("key", () => secretKeyBase64);
    map.putIfAbsent("data", () => "via");
    try {
      result = await Aesflutterplugin.encrypt(map);
    } on PlatformException {
      result = 'Failed to get platform version.';
    }
    print("result" + result);
    try {
      map = new Map();
      map.putIfAbsent("key", () =>secretKeyBase64);
      map.putIfAbsent("data", () => result);
      result = await Aesflutterplugin.decrypt(map);
    } on PlatformException {
      result = 'Failed to get platform version.';
    }
    print("result" + result);

    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = await Aesflutterplugin.platformVersion;
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

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: $_platformVersion\n'),
        ),
      ),
    );
  }
}
