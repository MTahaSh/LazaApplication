package com.example.lazaapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Cart extends Activity implements CartAdapter.OnItemRemoveListener {

    Button checkout_btn;
    private RecyclerView recyclerViewCart;
    private List<Item> cartItems;
    private CartAdapter cartAdapter;
    private SessionManagement sessionManagement;
    private MyDatabaseHelper myDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        sessionManagement = new SessionManagement(getApplicationContext());
        myDB = new MyDatabaseHelper(this);

        int userId = sessionManagement.getSession();

        recyclerViewCart = findViewById(R.id.recycler_view_cart);
        checkout_btn = findViewById(R.id.button_checkout);

        // Fetch cart items for the user
        cartItems = myDB.getCartItems(userId);

        // Set up RecyclerView with the CartAdapter
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(cartItems, this);
        recyclerViewCart.setAdapter(cartAdapter);

        // Handle checkout button click
        checkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Confirmation.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemRemove(int itemId) {
        // Implement your logic to remove the item from the database or cart list
        myDB.removeCartItem(itemId);

        // Update the adapter
        cartItems = myDB.getCartItems(sessionManagement.getSession());
        cartAdapter = new CartAdapter(cartItems, this);
        recyclerViewCart.setAdapter(cartAdapter);
    }
}
