package com.example.myapplicationgamemath;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivateKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.DataFormatException;

public class Activity_Game extends AppCompatActivity {

    private TextView text_score,text_chance,mt,text_time,text_num1,text_num2,result;
    private EditText input;
    int n = 0 , score = 0 , chance = 3 , time = 30 ;

    CountDownTimer timer;
    private static long time_mile=30000;
    boolean time_raning;
    Calendar calendar;

    final String KEY="KEY";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String BestScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        text_score=findViewById(R.id.textView_score);
        text_chance=findViewById(R.id.textView_chance);
        text_time=findViewById(R.id.textView_time);
        text_num1=findViewById(R.id.textView_num1);
        text_num2=findViewById(R.id.textView_scor);
        result=findViewById(R.id.textView_check);
        input=findViewById(R.id.editText_result);
        mt=findViewById(R.id.textView_mt);

        text_score.setText(String.valueOf(score));
        text_chance.setText(String.valueOf(chance));
        text_time.setText(String.valueOf(time));

        calendar=Calendar.getInstance();

        String formattedDate = CalendarUtils.getFormattedDate(calendar, "dd/MM HH:mm");

        text_num1.setText(String.valueOf(random_num1(9)));
        text_num2.setText(String.valueOf(random_num2(9)));


        starTimer();
        mt();

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (input.getText().toString()==null){
                timer.cancel();
                time_raning=false;
                time_mile=30000;

                int n1 = Integer.parseInt(String.valueOf(text_chance.getText()));
                int n2 = Integer.parseInt(String.valueOf(text_score.getText()));
                int n4 = Integer.parseInt(String.valueOf(input.getText()));

                int n5 = Integer.parseInt(String.valueOf(text_num1.getText()));
                int n6 = Integer.parseInt(String.valueOf(text_num2.getText()));
                text_num1.setText(String.valueOf(random_num1(9)));
                text_num2.setText(String.valueOf(random_num2(9)));
                n = m(n5, n6);

                if (n4 == n) {

                    text_score.setText(String.valueOf(score(n2)));
                    starTimer();
                    input.setText("");
                    Toast.makeText(Activity_Game.this, "corect", Toast.LENGTH_SHORT).show();

                    sharedPreferences=getSharedPreferences(KEY,MODE_PRIVATE);
                    BestScore=sharedPreferences.getString("Score","0");
                    editor=sharedPreferences.edit();

                    if (Integer.parseInt(BestScore)==0){

                        editor.putString("Score",text_score.getText().toString());
                        editor.putString("date",formattedDate);
                        editor.commit();

                    }else {

                        if ( Integer.parseInt(BestScore) >= Integer.parseInt(text_score.getText().toString()) ){

                            editor.putString("Score",BestScore);
                            editor.putString("date",formattedDate);
                            editor.commit();

                        }else{

                            editor.putString("Score",text_score.getText().toString());
                            editor.putString("date",formattedDate);
                            editor.commit();
                        }
                    }
                } else {
                    if (n1 == 1) {

                        Toast.makeText(Activity_Game.this, "end", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(Activity_Game.this, Activity_result.class);
                        i.putExtra("score", text_score.getText());
                        startActivity(i);

                    } else {
                        Toast.makeText(Activity_Game.this, "in corect", Toast.LENGTH_SHORT).show();
                        int c=chances(Integer.parseInt(text_chance.getText().toString()));
                        text_chance.setText(""+c);
                        starTimer();
                        text_num1.setText(String.valueOf(random_num1(9)));
                        text_num2.setText(String.valueOf(random_num2(9)));
                        n5 = Integer.parseInt(String.valueOf(text_num1.getText()));
                        n6 = Integer.parseInt(String.valueOf(text_num2.getText()));
                        n = m(n5, n6);
                        input.setText("");

                    }
                }
                }else {
                    Toast.makeText(Activity_Game.this, "Write Number", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public int random_num1(int n1){

        int random = new Random().nextInt(n1)+1;

        return random ;
    }

    public int random_num2(int n1){

        int random = new Random().nextInt(n1)+1;

        return random ;
    }

    public void starTimer(){


        timer =new CountDownTimer(time_mile,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                time_mile=millisUntilFinished;
                int scond= (int) ((time_mile/1000)%60);
                String format=String.format(Locale.getDefault(),"%02d",scond);
                text_time.setText(format);
            }

            @Override
            public void onFinish() {

                timer.cancel();
                time_raning=false;
                time_mile=30000;

                int n1 = Integer.parseInt(String.valueOf(text_chance.getText()));
                int n5 = Integer.parseInt(String.valueOf(text_num1.getText()));
                int n6 = Integer.parseInt(String.valueOf(text_num2.getText()));
                text_num1.setText(String.valueOf(random_num1(9)));
                text_num2.setText(String.valueOf(random_num2(9)));
                n = m(n5, n6);

                if (n1 == 1) {


                    Toast.makeText(Activity_Game.this, "time out", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Activity_Game.this, Activity_result.class);
                    i.putExtra("score", text_score.getText());
                    startActivity(i);

                } else {
                    Toast.makeText(Activity_Game.this, "in corect", Toast.LENGTH_SHORT).show();
                    int c=chances(Integer.parseInt(text_chance.getText().toString()));
                    text_chance.setText(""+c);
                    starTimer();
                    text_num1.setText(String.valueOf(random_num1(9)));
                    text_num2.setText(String.valueOf(random_num2(9)));
                    n5 = Integer.parseInt(String.valueOf(text_num1.getText()));
                    n6 = Integer.parseInt(String.valueOf(text_num2.getText()));
                    n = m(n5, n6);
                    input.setText("");

                }

            }
        }.start();

        time_raning=true;
    }


    public int chances(int c){
        int co = c-1;
        return co;
    }


    public int score (int s){

        int score=s+10;
        return score;
    }

    public int m (int m1, int m2){

        Bundle texts =getIntent().getExtras();
        String text=texts.getString("num");

        int n= Integer.parseInt(text);
        int mlt;

        switch (n){
            case 1:
                mlt = m1 + m2;
                break;
            case 2:
                mlt = m1 - m2;
                break;
            case 3:
                mlt = m1 * m2;
                break;
            case 4:
                mlt = m1 / m2;
                break;
            default:
                mlt=0;
        }

        return mlt;
    }



    public void mt(){

        Bundle texts =getIntent().getExtras();
        String text=texts.getString("num");
        int n= Integer.parseInt(text);

        Log.d("TAG", String.valueOf(n));

        switch (n){
            case 1:
                mt.setText("+");
                break;
            case 2:
                mt.setText("-");
                break;
            case 3:
                mt.setText("*");
                break;
            case 4:
                mt.setText("/");
                break;
        }
    }



    public static class CalendarUtils {
        public static String getFormattedDate(Calendar calendar, String dateFormat) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            return simpleDateFormat.format(calendar.getTime());
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        timer.cancel();
        time_raning=false;
        time_mile=30000;


    }
}