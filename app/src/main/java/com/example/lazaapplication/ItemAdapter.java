package com.example.lazaapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    private MyDatabaseHelper myDB;
    SessionManagement sessionManagement;
    public ItemAdapter(Context context, List<Item> items) {
        super(context, 0, items);
        myDB = new MyDatabaseHelper(context); // Correct context usage
        sessionManagement = new SessionManagement(context);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Item item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_activity, parent, false);
        }

        // Lookup view for data population
        ImageView itemImageView = convertView.findViewById(R.id.item_image);
        TextView nameTextView = convertView.findViewById(R.id.item_name);
        TextView priceTextView = convertView.findViewById(R.id.item_price);
        TextView quantityTextView = convertView.findViewById(R.id.item_quantity);
        Button addToCartButton = convertView.findViewById(R.id.add_to_cart_button);

        // Populate the data into the template view using the data object
        itemImageView.setImageResource(item.getImageResId());
        nameTextView.setText(item.getName());
        priceTextView.setText(String.valueOf(item.getPrice()));
        quantityTextView.setText(String.valueOf(item.getQuantity()));

        // Set OnClickListener for the "ADD TO CART" button
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click event here
                // You can pass the item's data to the onClick method
                // For example, you can pass the item's name

                int userId = sessionManagement.getSession();
                if(userId != -1)
                {
                    String itemName = item.getName();
                    int itemPrice = item.getPrice();
                    int itemQuantity = item.getQuantity();
                    myDB.addCart(itemName, itemPrice, itemQuantity, userId);
                    Toast.makeText(getContext(), "Item '" + itemName + "' added to cart", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "User session not found. Please log in.", Toast.LENGTH_SHORT).show();
                }



            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

}
