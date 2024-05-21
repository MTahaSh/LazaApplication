package com.example.lazaapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

public class Review extends Activity {

    private ImageButton back;
    private LinearLayout reviewsContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);

        back = findViewById(R.id.back_button);
        reviewsContainer = findViewById(R.id.reviews_container);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        int userId = sessionManagement.getSession();
        List<userReview> reviews = getReviewsForUser(userId);

        for (userReview review : reviews) {
            addReviewToContainer(review);
        }
    }

    private List<userReview> getReviewsForUser(int userId) {
        MyDatabaseHelper myDB = new MyDatabaseHelper(this);
        return myDB.getReviewsForUser(userId);
    }

    private void addReviewToContainer(userReview review) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View reviewView = inflater.inflate(R.layout.review_item, reviewsContainer, false);

        TextView reviewerNameTextView = reviewView.findViewById(R.id.reviewer_name);
        TextView reviewDescriptionTextView = reviewView.findViewById(R.id.review_description);

        // Set reviewer name and review description
        reviewerNameTextView.setText(review.getReviewerName());
        reviewDescriptionTextView.setText(review.getReviewDescription());

        // Add the review view to the reviews container
        reviewsContainer.addView(reviewView);
    }
}
