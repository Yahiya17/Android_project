package com.example.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.window.SplashScreen;

public class splashScreen extends AppCompatActivity {
    TextView txtacharya,txterp;
    RelativeLayout relativeLayout;
    Animation txtAnimation,layoutAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        txtAnimation= AnimationUtils.loadAnimation(splashScreen.this,R.anim.to_left);
        layoutAnimation=AnimationUtils.loadAnimation(splashScreen.this,R.anim.bottom_to_top);
        txtacharya=findViewById(R.id.acharya);
        txterp=findViewById(R.id.erp);
        relativeLayout=findViewById(R.id.relMain);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              relativeLayout.setVisibility(View.VISIBLE);
              relativeLayout.setAnimation(layoutAnimation);

              new Handler().postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      txtacharya.setVisibility(View.VISIBLE);
                      txterp.setVisibility(View.VISIBLE);
                      txtacharya.setAnimation(txtAnimation);
                      txterp.setAnimation(txtAnimation);
                  }
              },900);
            }
        },500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            Intent intent=new Intent(splashScreen.this,LoginScreen.class);
            startActivity(intent);
            finish();

            }
        },6000);

    }
}