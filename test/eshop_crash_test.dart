import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:eshop_crash/eshop_crash.dart';

void main() {
  const MethodChannel channel = MethodChannel('eshop_crash');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await EshopCrash.platformVersion, '42');
  });
}
