package com.example.lazaapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username_input, email_input, password_input;
    Button signup_button;
    Button gotoLogin_button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username_input = findViewById(R.id.nameEditText);
        email_input = findViewById(R.id.emailEditText);
        password_input = findViewById(R.id.passwordEditText);
        signup_button = (Button)findViewById(R.id.signupButton);
        gotoLogin_button = (Button)findViewById(R.id.loginButton);




        gotoLogin_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);

            }
        });


        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                String username = username_input.getText().toString().trim();
                String email = email_input.getText().toString().trim();
                String password = password_input.getText().toString().trim();

                if (validateInput(username, email, password)) {
//                    Log.d(TAG, "Adding user to database");
                    myDB.addUser(username, email, password);
                } else {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
//                myDB.addUser(username_input.getText().toString().trim(), email_input.getText().toString().trim(), password_input.getText().toString().trim());

            }
        });

    }


    private boolean validateInput(String username, String email, String password) {
        return !username.isEmpty() && !email.isEmpty() && !password.isEmpty();
    }

}