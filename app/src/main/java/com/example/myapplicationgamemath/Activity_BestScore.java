package com.example.myapplicationgamemath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_BestScore extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    final String KEY = "KEY";
    TextView textView,textView_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_score);

        textView=findViewById(R.id.textView_scor);
        textView_date=findViewById(R.id.textView2);


        sharedPreferences=getSharedPreferences(KEY,MODE_PRIVATE);
        String BestScore = sharedPreferences.getString("Score","0");
        String date = sharedPreferences.getString("date",null);

        textView.setText(BestScore);
        textView_date.setText(date);
    }
}