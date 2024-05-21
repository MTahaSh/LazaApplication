package com.example.lazaapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AddReview extends Activity {

    Button submit_review;
    EditText name;
    EditText review;

    ImageButton back_btn;

    private SessionManagement sessionManagement;
    private MyDatabaseHelper myDB;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_review);

        sessionManagement = new SessionManagement(getApplicationContext());
        myDB = new MyDatabaseHelper(this);

        int userId = sessionManagement.getSession();

        name = findViewById(R.id.edit_text_name);
        review = findViewById(R.id.edit_text_describe_experience);
        back_btn = findViewById(R.id.image_button_back);
        submit_review = findViewById(R.id.button_submit_review);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
            }
        });


        submit_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name = name.getText().toString().trim();
                String user_review = review.getText().toString().trim();

                if (user_name.isEmpty() || user_review.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in both fields", Toast.LENGTH_SHORT).show();
                } else {
                    myDB.addReview(user_name, user_review, userId);
                    Toast.makeText(getApplicationContext(), "Review Added", Toast.LENGTH_SHORT).show();
                    // Optionally, you can clear the input fields after submission
                    name.setText("");
                    review.setText("");
                }

            }
        });



    }
}
