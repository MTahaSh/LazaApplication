package com.example.lazaapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Login extends Activity {

    Button login_btn;
    Button btn1;

    EditText email, password;
    MyDatabaseHelper myDB = new MyDatabaseHelper(Login.this);

    SessionManagement sessionManagement;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        login_btn = (Button)findViewById(R.id.loginApply);
        btn1 = (Button)findViewById(R.id.goToSignUp);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        sessionManagement = new SessionManagement(getApplicationContext());


        login_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String user_email = email.getText().toString().trim();
                String user_password = password.getText().toString().trim();

                if (validateInput(user_email, user_password)) {
//                    Log.d(TAG, "Adding user to database");
//                    myDB.addUser(email, password);
                    Boolean checkuserpass = myDB.validateUser(user_email,user_password);
                    int userid = myDB.getUserId(user_email,user_password);
                    if(checkuserpass == true)
                    {
                        Toast.makeText(Login.this, "Sign in Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        sessionManagement.saveSession(userid);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(Login.this, "Invalid Email or Password",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }


            }
        });


        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);


            }
        });

    }


    private boolean validateInput(String email, String password) {
        return  !email.isEmpty() && !password.isEmpty();
    }
}
