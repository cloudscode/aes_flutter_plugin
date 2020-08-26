import 'dart:async';

import 'package:flutter/services.dart';

class Aesflutterplugin {
  static const MethodChannel _channel =
      const MethodChannel('aesflutterplugin');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> encrypt(Map<String, String> map) async {
    final String version = await _channel.invokeMethod('encrypt', map);
    return version;
  }

  static Future<String> decrypt(Map<String, String> map) async {
    final String version = await _channel.invokeMethod('decrypt', map);
    return version;
  }

}
