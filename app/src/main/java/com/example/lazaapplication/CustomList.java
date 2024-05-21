package com.example.lazaapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CustomList extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_cart);

        ListView listView = findViewById(R.id.item_list_view);


        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1,"Shoes", 50, 1, R.drawable.shoes1)); // Replace with your image resources
        itemList.add(new Item(2,"Shorts", 25, 1, R.drawable.shorts3));


        ItemAdapter adapter = new ItemAdapter(this, itemList);
        listView.setAdapter(adapter);



    }
}
