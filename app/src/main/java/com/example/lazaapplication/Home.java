package com.example.lazaapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class Home extends Activity {


    ImageButton cart;
    ImageButton add_review_btn;
    ImageButton review;

    Button ViewAll;

    ImageButton logout;

    SessionManagement sessionManagement;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        cart = (ImageButton) findViewById(R.id.cart_button);
        add_review_btn = findViewById(R.id.add_review_top_button);
        review = findViewById(R.id.menu_button);
        ViewAll = findViewById(R.id.view_all_button);
        logout = findViewById(R.id.home_button);
        sessionManagement = new SessionManagement(getApplicationContext());


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Login.class);
                sessionManagement.removeSession();
                Toast.makeText(Home.this, "Logging out!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


        ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CustomList.class);
                startActivity(intent);
            }
        });


        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Review.class);
                startActivity(intent);
            }
        });

        add_review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddReview.class);
                startActivity(intent);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Cart.class);
                startActivity(intent);
            }
        });


    }
}
