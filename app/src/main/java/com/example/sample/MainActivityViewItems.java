package com.example.sample;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sample.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewItems extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_items);

        ArrayList<String> list = new ArrayList();
        listView = findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);

        MyDbHandler db = new MyDbHandler(MainActivityViewItems.this);
        Item biscuit = new Item();
        biscuit.setItem_id(1);
        biscuit.setItem_name("GoodDay");
        biscuit.setItem_weight("500");
        db.addItem(biscuit);

//        biscuit.setItem_name("Marie");
//        biscuit.setItem_weight("1000");
//        int affectedRows = db.updateItem(biscuit);
//        Log.d("dbharry","No of affected rows are: "+affectedRows);

        Item tea = new Item();
        tea.setItem_id(2);
        tea.setItem_name("Red Label");
        tea.setItem_weight("1000");
        db.addItem(tea);

        Item coffee = new Item();
        coffee.setItem_id(3);
        coffee.setItem_name("Nescafe");
        coffee.setItem_weight("800");
        db.addItem(coffee);

        Item chocolate = new Item();
        chocolate.setItem_id(4);
        chocolate.setItem_name("Dairy Milk");
        chocolate.setItem_weight("550");
        db.addItem(chocolate);

//        db.deleteContact(2); // Delete contact with id 2

        List<Item> allItems = db.getAllItems();
        for(Item item: allItems){

            Log.d("dbdhruv","Id: " + item.getItem_id() + "\n" + "Name: " + item.getItem_name() + "\n" + "Weight: " + item.getItem_weight() + "\n");
//            Log.d("dbdhruv","Name: " + item.getItem_name());
            list.add(item.getItem_name());
        }

    }
}
