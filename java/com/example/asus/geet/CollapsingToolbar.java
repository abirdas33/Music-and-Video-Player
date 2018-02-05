package com.example.asus.geet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class CollapsingToolbar extends AppCompatActivity implements View.OnClickListener {
    private CardView musicCard,videoCard,saveCard,feedbackCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
        musicCard=(CardView)findViewById(R.id.musiccard);
        videoCard=(CardView)findViewById(R.id.videocard);
        saveCard=(CardView)findViewById(R.id.savecard);
        feedbackCard=(CardView)findViewById(R.id.feedbackcard);

        musicCard.setOnClickListener(this);
        videoCard.setOnClickListener(this);
        saveCard.setOnClickListener(this);
        feedbackCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.musiccard:
                i=new Intent(getApplicationContext(),splash.class);
                startActivity(i);
                break;
            case R.id.videocard:
                i=new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(i);
                break;
            case R.id.savecard:
                i=new Intent(getApplicationContext(),Main3Activity.class);
                startActivity(i);
                break;
            case R.id.feedbackcard:
                i=new Intent(getApplicationContext(),RatingActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
