package com.example.sqlitedemo;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import android.widget.Button;

import com.example.sqlitedemo.control.QuizLogik;
import com.example.sqlitedemo.model.FlagsModel;

import org.junit.Test;
import org.mockito.Mockito;

public class ControlTest {



    @Test
    public void answerControlTest(){
        FlagsModel model = new FlagsModel(1, "flag_id", "flag_image");
        QuizLogik quizLogik = new QuizLogik();
        quizLogik.setCorrectFlag(model);
        Button mockButton = Mockito.mock(Button.class);
        Mockito.when(mockButton.getText()).thenReturn("flag_id");
        assertTrue(quizLogik.answerControl(mockButton));
    }
}
