package com.example.grocery;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.grocery.activities.LoginActivity;
import com.example.grocery.activities.MainActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This method is used so that your splash activity
        //can cover the entire screen.

        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent i = new Intent(SplashScreen.this,
//                        LoginActivity.class);
//                //Intent is used to switch from one activity to another.
//                startActivity(i);
//                //invoke the SecondActivity.
//                finish();
                //the current activity will get finished.

//                SharedPreferences sp = getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
//                boolean b = sp.getBoolean("haslogin", false);
//
//                if (b){
//                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
//                    finish();
//                }
//                else {
//                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
//                    finish();
//                }
                SessionsManager sessionsManager = new SessionsManager(SplashScreen.this);
                boolean b = sessionsManager.userSession.getBoolean("IsLoggedIn", false);
                if (b){
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                }
                else {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                }
                finish();

            }
        }, 2000);
    }
}