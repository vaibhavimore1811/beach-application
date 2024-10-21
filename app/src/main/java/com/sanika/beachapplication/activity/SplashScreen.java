package com.sanika.beachapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sanika.beachapplication.R;
import com.sanika.beachapplication.constance.AppLocaleLanguage;
import com.sanika.beachapplication.constance.SharedPref;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_SCREEN=4000;
    private Animation topAnim , bottomAnim;
    private ImageView logo;
    private TextView app_Name , slogan;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sharedPreferences = SharedPref.getSharedPref(SplashScreen.this);

        setContentView(R.layout.activity_splash_screen);
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        logo=findViewById(R.id.id1_logo);
        app_Name=findViewById(R.id.id2_appName);
        slogan=findViewById(R.id.id3_slogan);
        logo.setAnimation(topAnim);
        app_Name.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);
        String language = SharedPref.getString(sharedPreferences, SharedPref.locale);

        AppLocaleLanguage.setApplicationLocale(SplashScreen.this,"en");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent intent = new Intent(SplashScreen.this, OnBoardingscreen.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}