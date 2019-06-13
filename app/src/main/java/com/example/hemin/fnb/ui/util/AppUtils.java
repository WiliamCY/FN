package com.example.hemin.fnb.ui.util;

import android.app.Application;
import android.content.Context;

public class AppUtils {

    private static Application application;

    public static void setApplication(Application application) {
        if (AppUtils.application != null) {
            throw new IllegalStateException("application already holded 'application'.");
        }
        AppUtils.application = application;
    }
    public static Context getContext() {
        return application.getApplicationContext();
    }
}
