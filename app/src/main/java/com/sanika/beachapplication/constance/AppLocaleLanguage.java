package com.sanika.beachapplication.constance;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

public class AppLocaleLanguage {


    public static void setApplicationLocale(Context context, String locale) {
        Resources res = context.getResources();
// Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(locale.toLowerCase())); // API 17+ only.
        }
// Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
        SharedPreferences sharedPreferences = SharedPref.getSharedPref(context);
        SharedPreferences sharedPref = SharedPref.getSharedPref(context);
        SharedPref.setString(sharedPref, SharedPref.locale, locale);

    }
}
