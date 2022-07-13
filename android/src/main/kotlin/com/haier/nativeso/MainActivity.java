package com.haier.nativeso;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity {

    // Used to load the 'nativeso' library on application startup.
    static {
        System.loadLibrary("nativeso");
    }



    /**
     * A native method that is implemented by the 'nativeso' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String makeCrash();

}