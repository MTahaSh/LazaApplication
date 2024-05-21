package com.example.lazaapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "lazaApp.db";
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_NAME = "my_user";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_EMAIL = "user_email";
    private static final String COLUMN_PASSWORD = "user_password";
    private static final String COLUMN_USERNAME = "user_username";

    private static final String CART_TABLE_NAME = "item_cart";
    private static final String COLUMN_CID = "_cid";
    private static final String COLUMN_ITEM = "item_name";
    private static final String COLUMN_QUANTITY = "item_quantity";

    private static final String COLUMN_PRICE = "item_price";


    private static final String REVIEW_TABLE_NAME = "user_review";
    private static final String COLUMN_RID = "_rid";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_REVIEW = "review";






    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_EMAIL + " TEXT, " +
                        COLUMN_PASSWORD + " TEXT, " +
                        COLUMN_USERNAME + " TEXT);";


        String query_cart = "CREATE TABLE " + CART_TABLE_NAME +
                " (" + COLUMN_CID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ITEM + " TEXT, " +
                COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_PRICE + " INTEGER, " + COLUMN_ID + " INTEGER);";


        String query_review = "CREATE TABLE " + REVIEW_TABLE_NAME +
                " (" + COLUMN_RID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_NAME + " TEXT, " +
                COLUMN_USER_REVIEW + " TEXT, " +
                COLUMN_ID + " INTEGER);";


        db.execSQL(query);
        db.execSQL(query_cart);
        db.execSQL(query_review);


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CART_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + REVIEW_TABLE_NAME);
        onCreate(db);
    }

    void addUser(String username, String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERNAME,username);
        cv.put(COLUMN_EMAIL,email);
        cv.put(COLUMN_PASSWORD,password);
        long result = db.insert(TABLE_NAME,null, cv);

        if(result == -1)
        {
//            Log.d(TAG, "Failed to insert data");
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
            System.out.println("Failed");
        }
        else {
            Toast.makeText(context, "Added Successfully!",Toast.LENGTH_SHORT).show();
            System.out.println("Added");
        }
    }


    void addReview(String username, String review, int userId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME,username);
        cv.put(COLUMN_USER_REVIEW,review);
        cv.put(COLUMN_ID,userId);
        long result = db.insert(REVIEW_TABLE_NAME,null, cv);

        if(result == -1)
        {
//            Log.d(TAG, "Failed to insert data");
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
            System.out.println("Failed");
        }
        else {
            Toast.makeText(context, "Review Added Successfully!",Toast.LENGTH_SHORT).show();
            System.out.println("Added");
        }
    }


    public List<userReview> getReviewsForUser(int userId) {
        List<userReview> reviews = new ArrayList<>();

        // Select query to fetch reviews for the given userId
        String selectQuery = "SELECT * FROM " + REVIEW_TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(userId)});

        // Loop through the cursor to retrieve review data
        if (cursor != null && cursor.moveToFirst()) {
            int reviewerIdIndex = cursor.getColumnIndex(COLUMN_RID);
            int reviewerNameIndex = cursor.getColumnIndex(COLUMN_USER_NAME);
            int reviewDescriptionIndex = cursor.getColumnIndex(COLUMN_USER_REVIEW);

            do {
                // Check if columns exist in the cursor
                if (reviewerIdIndex != -1 && reviewerNameIndex != -1 && reviewDescriptionIndex != -1) {
                    // Retrieve review data from the cursor
                    int reviewerId = cursor.getInt(reviewerIdIndex);
                    String reviewerName = cursor.getString(reviewerNameIndex);
                    String reviewDescription = cursor.getString(reviewDescriptionIndex);

                    // Create a Review object and add it to the list
                    userReview review = new userReview(reviewerId, reviewerName, reviewDescription);
                    reviews.add(review);
                }
            } while (cursor.moveToNext());

            // Close the cursor after use
            cursor.close();
        }

        // Return the list of reviews
        return reviews;
    }


    public void removeCartItem(int itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CART_TABLE_NAME, COLUMN_CID + " = ?", new String[]{String.valueOf(itemId)});
        db.close();
    }


    void addCart(String item_name, int item_price, int item_quantity, int user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ITEM,item_name);
        cv.put(COLUMN_PRICE,item_price);
        cv.put(COLUMN_QUANTITY,item_quantity);
        cv.put(COLUMN_ID,user_id);
        long result = db.insert(CART_TABLE_NAME,null, cv);

        if(result == -1)
        {
//            Log.d(TAG, "Failed to insert data");
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
            System.out.println("Failed");
        }
        else {
            Toast.makeText(context, "Added to CART Successfully!",Toast.LENGTH_SHORT).show();
            System.out.println("Added");
        }
    }





    public List<Item> getCartItems(int userId) {
        List<Item> cartItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CART_TABLE_NAME + " WHERE " + COLUMN_ID + " = ?", new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            do {
                int itemIdIndex = cursor.getColumnIndex(COLUMN_CID);
                int itemNameIndex = cursor.getColumnIndex(COLUMN_ITEM);
                int itemPriceIndex = cursor.getColumnIndex(COLUMN_PRICE);
                int itemQuantityIndex = cursor.getColumnIndex(COLUMN_QUANTITY);

                if (itemNameIndex == -1 || itemPriceIndex == -1 || itemQuantityIndex == -1) {
                    cursor.close();
                    return cartItems;
                }


                int itemId = cursor.getInt(itemIdIndex);
                String itemName = cursor.getString(itemNameIndex);
                int itemPrice = cursor.getInt(itemPriceIndex);
                int itemQuantity = cursor.getInt(itemQuantityIndex);

                int itemImageResId;
                if ("shoes".equalsIgnoreCase(itemName)) {
                    itemImageResId = R.drawable.shoes1;
                } else if ("shorts".equalsIgnoreCase(itemName)) {
                    itemImageResId = R.drawable.shorts3;
                } else {
                    itemImageResId = R.drawable.ic_launcher_foreground; // Default image if item name doesn't match
                }

                cartItems.add(new Item(itemId,itemName, itemPrice, itemQuantity, itemImageResId));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cartItems;
    }






    public Boolean validateUser(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + COLUMN_EMAIL +
                " = ? and " + COLUMN_PASSWORD + " = ?", new String[]{email,password});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else {
            return false;
        }
    }


    public int getUserId(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_ID + " FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{email, password});

        int userId = -1; // Default value if no user is found
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
        }
        cursor.close();
        return userId;
    }


    Cursor getAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db != null)
        {
            cursor = db.rawQuery(query,null);
        }
        return cursor;

    }




}
