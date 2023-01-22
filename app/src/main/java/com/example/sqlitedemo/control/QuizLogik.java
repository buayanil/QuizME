package com.example.sqlitedemo.control;

import android.content.Context;
import android.widget.Button;

import com.example.sqlitedemo.model.FlagsDAO;
import com.example.sqlitedemo.model.FlagsDatabase;
import com.example.sqlitedemo.model.FlagsModel;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Database aufrufen und die Methoden für die Fragen aufrufen
 */
public class QuizLogik implements QuizLogikInt{


    private FlagsDatabase fdatabase;

    public void setCorrectFlag(FlagsModel correctFlag) {
        this.correctFlag = correctFlag;
    }

    private FlagsModel correctFlag;

    private ArrayList<FlagsModel> wrongOptionList;

    private HashSet<FlagsModel> mixOptions = new HashSet<>();
    private ArrayList<FlagsModel> options = new ArrayList<>();


    public FlagsModel getCorrectFlag() {
        return correctFlag;
    }

    @Override
    public ArrayList<FlagsModel> loadQuestion(int question, ArrayList<FlagsModel> questionsList) {
        correctFlag = questionsList.get(question);
        wrongOptionList = new FlagsDAO().getRandomThreeOptions(fdatabase, correctFlag.getFlag_id());
        mixOptions.clear();
        mixOptions.add(correctFlag);
        mixOptions.add(wrongOptionList.get(0));
        mixOptions.add(wrongOptionList.get(1));
        mixOptions.add(wrongOptionList.get(2));

        options.clear();
        for(FlagsModel flg : mixOptions){
            options.add(flg);
        }
        return options;
    }

    @Override
    public boolean answerControl(Button button) {
        String buttonText = button.getText().toString();
        String correctAnswer = correctFlag.getFlag_name();
        return buttonText.equals(correctAnswer);
    }

    @Override
    public ArrayList<FlagsModel> loadDatabase(Context context) {
        fdatabase = new FlagsDatabase(context);
        ArrayList<FlagsModel> questionsList = new FlagsDAO().getRandomTenQuestion(fdatabase);
        return questionsList;
    }

}