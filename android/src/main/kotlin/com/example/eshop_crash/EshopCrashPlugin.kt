package com.example.eshop_crash

import android.util.Log
import androidx.annotation.NonNull


import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import kotlin.concurrent.thread

/** EshopCrashPlugin */
class EshopCrashPlugin : FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "eshop_crash")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        Log.i("eshopcrashplugintag","call.method ${call.method}" );
        when(call.method){
            "getPlatformVersion"->{
                result.success("Android ${android.os.Build.VERSION.RELEASE}")
                return;
            }
            "throw" -> {
                thread(isDaemon = true) {
                    throw Exception("Thrown from Kotlin!")
                }
            }

            "throwJava" -> {
                CrashMaker().javaCException()
            }

            "anr" -> {
                Thread.sleep(6_000)
            }
            "capture" -> {
                try {
                    throw RuntimeException("Catch this exception!")
                } catch (e: Exception) {
//                    Sentry.captureException(e)
                }
            }
            "crash" -> {
                crash()
            }

            "crash_so" -> {
            }

            "cpp_capture_message" -> {
                message()
            }
            "platform_exception" -> {
                result.success(Thread.currentThread().getStackTrace())
            }
            else -> {
                result.notImplemented()
            }
        }
        result.success("")
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    private external fun crash()
    private external fun message()

    companion object {
        init {
            Log.i("eshopcrashplugintag","loadLibrary native sample" );
//            System.loadLibrary("native-sample")
        }
    }
}
