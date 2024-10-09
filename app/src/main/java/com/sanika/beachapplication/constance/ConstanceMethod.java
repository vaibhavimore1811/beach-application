package com.sanika.beachapplication.constance;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.sanika.beachapplication.R;


public class ConstanceMethod {


    public static Dialog ShowProgressDialog(Activity activity) {
        Dialog dialog = new Dialog(activity);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.layout_progress_dailoag);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        return dialog;
    }

    // full screen application
    public static void Fullscreen(SharedPreferences sharedPreferences, Activity activity) {
        boolean isNightModeEnabled = sharedPreferences.getBoolean(SharedPref.NIGHT_MODE, false);

        if (isNightModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        View decorView = activity.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(activity, R.color.primaryColor));
    }

    public static void ShowFailDialog(String message, Activity activity) {
        Dialog Alertdialog = new Dialog(activity);
        Alertdialog.setContentView(R.layout.layout_fail_alert);

        Alertdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView txtSuccessMain = Alertdialog.findViewById(R.id.txtSuccessMain);
        txtSuccessMain.setText(message);
        AppCompatButton btnSuccessOk = Alertdialog.findViewById(R.id.btnSuccessOk);
        btnSuccessOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alertdialog.cancel();


            }
        });
        Alertdialog.setCancelable(false);
        Alertdialog.show();
    }

  //start new activity with fresh top
    public static void startNewActivityWithFinish(Activity currentActivity, Class<? extends Activity> newActivityClass) {
        Intent intent = new Intent(currentActivity, newActivityClass);
        currentActivity.startActivity(intent);
        currentActivity.finish();
    }
    //start new activity with all top
    public static void startNewActivity(Activity currentActivity, Class<? extends Activity> newActivityClass) {
        Intent intent = new Intent(currentActivity, newActivityClass);
        currentActivity.startActivity(intent);

    }


}
