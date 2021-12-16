package com.example.quizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {

    public static Context context_main;
    public int score = 0;
    private int Num = 0;
    private Button trueButton;
    private Button falseButton;
    private Button finishButton;
    private TextView questionTextView;

    private int currentQuestionIndex = 0;


    private Question[] questionBank = new Question[]{
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, true),
            new Question(R.string.question_3, false),
            new Question(R.string.question_4, false),
            new Question(R.string.question_5, true),
            new Question(R.string.question_6, false),
            new Question(R.string.question_7, false),
            new Question(R.string.question_8, false),
            new Question(R.string.question_9, false),
            new Question(R.string.question_10, false),
            new Question(R.string.question_11, true)
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        context_main = this;

        Button imageButton2 = (Button) findViewById(R.id.finish_button);
        imageButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FinishActivity.class);
                startActivity(intent);
            }
        });

        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.ture_buttoon);
        questionTextView = findViewById(R.id.answer_text_view);
        finishButton = findViewById(R.id.finish_button);


        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ture_buttoon:
                checkAnswer(true);
                break;

            case R.id.false_button:
                checkAnswer(false);
                break;

        }
    }

    private void updateQuestion() {
        Log.d("Current", "Onclick" + currentQuestionIndex);
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }

    private void checkAnswer(boolean userChoosenCorrect) {
        boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();

        int toastMessageId = 0;
        if (userChoosenCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
            Num += 1;
            score += 1;

            if(Num == 11){

                finishButton.setVisibility(View.VISIBLE);
                trueButton.setVisibility(View.INVISIBLE);
                falseButton.setVisibility(View.INVISIBLE);
            } else {

                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
                updateQuestion();
            }

        } else {
            toastMessageId = R.string.wrong_answer;
            Num += 1;

            if(Num == 11){

                finishButton.setVisibility(View.VISIBLE);
                trueButton.setVisibility(View.INVISIBLE);
                falseButton.setVisibility(View.INVISIBLE);
            } else {

                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
                updateQuestion();
            }

        }
        Toast.makeText(SubActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
    }
}
