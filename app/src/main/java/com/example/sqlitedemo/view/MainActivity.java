package com.example.sqlitedemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.model.DBHelper;
import com.example.sqlitedemo.model.Player;
import com.example.sqlitedemo.model.PlayerInt;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        signup=findViewById(R.id.signup);
        signin=findViewById(R.id.signin);
        DB = new DBHelper(this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                PlayerInt player = new Player(user, pass);

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)){
                    Toast.makeText(MainActivity.this, "All fields required", Toast.LENGTH_LONG).show();
                }
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkUserName(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(player);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                               Toast.makeText(MainActivity.this, "Registration Failed!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Password are not matching", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}