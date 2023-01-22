package com.example.sqlitedemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.control.QuizLogik;

import com.example.sqlitedemo.model.FlagsModel;

import java.security.AccessControlContext;
import java.util.ArrayList;

/**
 *
 */
public class QuizActivity extends AppCompatActivity {

    private TextView textViewCorrect, textViewWrong, textViewEmpty, textViewQuestion;
    private ImageView imageViewFlag, imageViewNext;
    private Button buttonA, buttonB, buttonC, buttonD;

    //private FlagsDatabase fdatabase;
    private ArrayList<FlagsModel> questionsList;

    private int correct = 0;
    private int wrong = 0;
    private int empty = 0;
    private int question = 0;
    /*
    private FlagsModel correctFlag;

    private ArrayList<FlagsModel> wrongOptionList;


    private HashSet<FlagsModel> mixOptions = new HashSet<>();
     */
    private ArrayList<FlagsModel> options = new ArrayList<>();

    QuizLogik quizLogik = new QuizLogik();
    boolean buttonControl = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewCorrect = findViewById(R.id.textViewCorrect);
        textViewWrong = findViewById(R.id.textViewWrong);
        textViewEmpty = findViewById(R.id.textViewEmpty);
        textViewQuestion = findViewById(R.id.textViewQuestion);

        imageViewFlag = findViewById(R.id.imageViewFlag);
        imageViewNext = findViewById(R.id.imageViewNext);

        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);


        questionsList = quizLogik.loadDatabase(QuizActivity.this);


        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerControl(buttonA);
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerControl(buttonB);
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerControl(buttonC);
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerControl(buttonD);
            }
        });

        imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question++;

                if(!buttonControl && question<10){
                    empty++;
                    textViewEmpty.setText("Empty: "+empty);
                    loadQuestion();
                } else if (buttonControl && question<10){
                    loadQuestion();

                    buttonA.setClickable(true);
                    buttonB.setClickable(true);
                    buttonC.setClickable(true);
                    buttonD.setClickable(true);

                    buttonA.setBackgroundColor(getResources().getColor(R.color.white));
                    buttonB.setBackgroundColor(getResources().getColor(R.color.white));
                    buttonC.setBackgroundColor(getResources().getColor(R.color.white));
                    buttonD.setBackgroundColor(getResources().getColor(R.color.white));
                }
                else if(question==10){
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("correct", correct);
                    intent.putExtra("wrong", wrong);
                    intent.putExtra("empty", empty);
                    startActivity(intent);
                    finish();
                }
                buttonControl = false;
            }
        });
        loadQuestion();

    }

    public void loadQuestion(){
        textViewQuestion.setText("Question: "+(question+1));
        options = quizLogik.loadQuestion(question, questionsList);
        imageViewFlag.setImageResource(getResources().getIdentifier(quizLogik.getCorrectFlag().getFlag_image(), "drawable", getPackageName()));
        buttonA.setText(options.get(0).getFlag_name());
        buttonB.setText(options.get(1).getFlag_name());
        buttonC.setText(options.get(2).getFlag_name());
        buttonD.setText(options.get(3).getFlag_name());

    }

    public void answerControl(Button button){
        String correctAnswer = quizLogik.getCorrectFlag().getFlag_name();

        if(quizLogik.answerControl(button)){
            correct++;
            button.setBackgroundColor(Color.GREEN);
        } else {
            wrong++;
            button.setBackgroundColor(Color.RED);

            if(buttonA.getText().toString().equals(correctAnswer)){
                buttonA.setBackgroundColor(Color.GREEN);
            }
            if(buttonB.getText().toString().equals(correctAnswer)){
                buttonB.setBackgroundColor(Color.GREEN);
            }
            if(buttonC.getText().toString().equals(correctAnswer)){
                buttonC.setBackgroundColor(Color.GREEN);
            }
            if(buttonD.getText().toString().equals(correctAnswer)){
                buttonD.setBackgroundColor(Color.GREEN);
            }
        }

        buttonA.setClickable(false);
        buttonB.setClickable(false);
        buttonC.setClickable(false);
        buttonD.setClickable(false);

        textViewCorrect.setText("Correct: "+correct);
        textViewWrong.setText("Wrong: "+wrong);

        buttonControl=true;
    }
}