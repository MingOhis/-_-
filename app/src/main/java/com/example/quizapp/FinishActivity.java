package com.example.quizapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FinishActivity extends AppCompatActivity {

    int score2 = ((SubActivity)SubActivity.context_main).score;
    private TextView scoreNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        scoreNum = findViewById(R.id.score_num);

        scoreNum.setText(score2);
    }
}
