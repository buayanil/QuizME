package com.example.sqlitedemo.control;


import android.content.Context;
import android.widget.Button;

import com.example.sqlitedemo.model.FlagsModel;

import java.util.ArrayList;



public interface QuizLogikInt{
    /**
     *  legt die Frage und die Antworten von den Daten aus FlagsDAO fest.
     * @param question
     * @param questionsList
     * @return
     */
    ArrayList<FlagsModel> loadQuestion(int question, ArrayList<FlagsModel> questionsList);
     /**
      * kontrolliert die Antwrot des Spielers und zählt den Score hoch
      * @param button die Antwort des Spielers
      */
    boolean answerControl(Button button);
    /**
     * lädt die Datnbank
     * @param context die Activity, wo die Datenbank fgeladen soll.
     * @return
     */
    ArrayList<FlagsModel> loadDatabase(Context context);

}
