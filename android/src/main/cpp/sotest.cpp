#include <jni.h>
#include <android/log.h>
//#include <sentry.h>

#define TAG "sentry-sample"

extern "C" {

void willCrash() {
    __android_log_print(ANDROID_LOG_WARN, TAG, "sowillCrash");
    char *ptr = 0;
    *ptr += 1;
}


void willCallAnother() {
    __android_log_print(ANDROID_LOG_WARN, TAG, "sowillCallAnother");
    willCrash();
}

JNIEXPORT void JNICALL Java_com_example_eshop_1crash_EshopCrashPlugin_crash(JNIEnv *env, jclass cls) {
    __android_log_print(ANDROID_LOG_WARN, TAG, "About to crash with a SEGFAULT in so");
    willCallAnother();
}

JNIEXPORT void JNICALL Java_io_sentry_samples_flutter_MainActivity_message(JNIEnv *env, jclass cls) {
     __android_log_print(ANDROID_LOG_WARN, TAG, "Sending message.");
//    sentry_value_t event = sentry_value_new_message_event(
//            /*   level */ SENTRY_LEVEL_INFO,
//            /*  logger */ "native",
//            /* message */ "message from C++!"
//    );
//    sentry_capture_event(event);
}

}
