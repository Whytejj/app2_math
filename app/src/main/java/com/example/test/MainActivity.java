package com.example.test;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.media.MediaPlayer;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.media.AudioManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    TextView sum;
    ArrayList<Integer> choices;
    int res_location;
    int x;
    Button b0;
    Button b1;
    Button b2;
    Button b3;
    int result;
    Button go;
    ImageView imageView;
    int score =0;
    int nq = 0;
    TextView record;
    public void bset(){
        choices = new ArrayList<Integer>();
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        result = a + b;
        Log.i("test", "result: "+result);

        sum = findViewById(R.id.sum);
        sum.setText(Integer.toString(a) + " + " + Integer.toString(b));

        res_location = rand.nextInt(4);
        Log.i("test", "bset: "+res_location);

        for(int i=0; i<4; i++){
            int wrong = rand.nextInt(41);
            if(i==res_location){
                choices.add(result);
            }
            else{
                while (wrong == result){
                    wrong = rand.nextInt(41);
                }
                choices.add(wrong);
            }
        }
        for(int i=0; i <4; i++) {
            Log.i("test", "array " +Integer.toString(choices.get(i)));
        }
        b0.setText(Integer.toString(choices.get(0)));
        b1.setText(Integer.toString(choices.get(1)));
        b2.setText(Integer.toString(choices.get(2)));
        b3.setText(Integer.toString(choices.get(3)));
    }
    public void start(View view){
        go.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        if(x ==0) {
            time();
            x=1;
        }
    }
    public void pick(View view){
       String tag = (String) view.getTag();
       Button picked = (Button) view;
       String result_string = Integer.toString(result);

       if (result_string.equals(picked.getText())){
            go.setText("Congratulations");
            go.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            score++;
            bset();

       }
       else{
           go.setText("oops");
           go.setVisibility(View.VISIBLE);
           imageView.setVisibility(View.VISIBLE);
           bset();
       }

       nq++;

       record.setText(Integer.toString(score)+"/"+Integer.toString(nq));
    }
    public void time(){
        new CountDownTimer(30000+1000,1000){
            TextView time = findViewById(R.id.time);
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(Long.toString((millisUntilFinished)/1000)+"s");
            }

            @Override
            public void onFinish() {
                time.setText("0s");
                go.setText("Times Up");
                go.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                time.setText("30s");
                score = 0;
                nq =0;
                x = 0;
                bset();
            }
        }.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go = findViewById(R.id.go);
        imageView = findViewById(R.id.imageView);
        record = findViewById(R.id.record);
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        x=0;
        bset();
    }

}
