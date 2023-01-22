package com.example.sqlitedemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.model.DatabaseCopyHelper;

public class HomeActivity extends AppCompatActivity {

    TextView textViewStart;
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewStart = findViewById(R.id.textViewStart);
        buttonStart = findViewById(R.id.buttonStart);

        copyDatabase();

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);

            }
        });
    }

    public void copyDatabase(){
        try{
            DatabaseCopyHelper helper = new DatabaseCopyHelper(HomeActivity.this);
            helper.createDataBase();
            helper.openDataBase();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}