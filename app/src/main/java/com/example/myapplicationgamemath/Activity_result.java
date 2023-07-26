package com.example.myapplicationgamemath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_result extends AppCompatActivity {

    TextView textView , button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textView=findViewById(R.id.textView_scor);
        button=findViewById(R.id.textView_check);



        Bundle texts= getIntent().getExtras();
        String text=texts.getString("score");
        textView.setText(text);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_result.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}