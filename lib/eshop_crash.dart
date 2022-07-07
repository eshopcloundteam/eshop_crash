
import 'dart:async';

import 'package:flutter/services.dart';

class EshopCrash {
  static const MethodChannel _channel =
      const MethodChannel('eshop_crash');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
