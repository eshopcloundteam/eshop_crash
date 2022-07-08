import 'package:eshop_crash/eshop_crash.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';

class CrashDebugPage extends StatefulWidget {
  CrashDebugPage({Key? key}) : super(key: key);

  @override
  _CrashDebugPageState createState() => _CrashDebugPageState();
}

class _CrashDebugPageState extends State<CrashDebugPage> {
  @override
  void initState() {
    super.initState();
    _init();
  }

  void _init() {}
  final channel = const MethodChannel('eshop_crash');

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Container(
        child: Column(
          children: <Widget>[
            ElevatedButton(
                onPressed: () async {
                  String version = await EshopCrash.platformVersion;
                  print('platformversion $version');
                },
                child: Text('getversion')),
            ElevatedButton(
                onPressed: () async {
                  execute('throw');
                },
                child: Text('kotlin throw uncaught exception')),
            ElevatedButton(
                onPressed: () async {
                  execute('crash');
                },
                child: Text('cpp throw uncaught exception'))


          ],
        ),
      ),
    );
  }

  Future<void> execute(String method) async {
    try {
      await channel.invokeMethod<void>(method);
    } catch (error, stackTrace) {
      // await Sentry.captureException(error, stackTrace: stackTrace);
    }
  }

}
