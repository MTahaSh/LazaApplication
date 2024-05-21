package com.example.lazaapplication;

public class userReview {
    private int reviewerId;
    private String reviewerName;
    private String reviewDescription;

    public userReview(int reviewerId, String reviewerName, String reviewDescription) {
        this.reviewerId = reviewerId;
        this.reviewerName = reviewerName;
        this.reviewDescription = reviewDescription;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }
}
