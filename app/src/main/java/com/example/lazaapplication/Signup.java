//package com.example.lazaapplication;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import androidx.annotation.Nullable;
//
//public class Signup extends Activity {
//
//    EditText username_input, email_input, password_input;
//    Button signup_button;
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        username_input = findViewById(R.id.nameEditText);
//        email_input = findViewById(R.id.emailEditText);
//        password_input = findViewById(R.id.passwordEditText);
//        signup_button = (Button)findViewById(R.id.signupButton);
//
//
//
//
//
//        signup_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MyDatabaseHelper myDB = new MyDatabaseHelper(Signup.this);
//                myDB.addUser(username_input.getText().toString().trim(), email_input.getText().toString().trim(), password_input.getText().toString().trim());
//
//            }
//        });
//
//    }
//
//}
