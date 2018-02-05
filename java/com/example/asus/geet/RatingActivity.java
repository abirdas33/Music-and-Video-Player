package com.example.asus.geet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hsalf.smilerating.SmileRating;

public class RatingActivity extends AppCompatActivity {
    public Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        submit=(Button)findViewById(R.id.feedback);
        SmileRating smileRating=(SmileRating)findViewById(R.id.smile_rating);
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley) {
                switch (smiley){
                    case SmileRating.BAD:
                        Toast.makeText(getApplicationContext(),"Bad",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(getApplicationContext(),"Good",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(getApplicationContext(),"Great",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(getApplicationContext(),"terrible",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(getApplicationContext(),"Okay",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Thanks For Your Feedback",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
