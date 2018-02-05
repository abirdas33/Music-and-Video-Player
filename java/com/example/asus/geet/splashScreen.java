package com.example.asus.geet;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class splashScreen extends AppCompatActivity {
    TextView im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        im=(TextView) findViewById(R.id.tv);

            im.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(splashScreen.this, MainActivity.class);
                    startActivity(i);
                }
            }, 3000);

    }
    @Override
    public void onBackPressed(){

       // super.onBackPressed();
        finish();

    }
}
