package com.example.sample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityViewInfo extends AppCompatActivity {

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_info);

        TextView name,id,weight,expiry,frequency;
        name = findViewById(R.id.item_name);
        id = findViewById(R.id.item_id);
        weight = findViewById(R.id.item_weight);
        expiry = findViewById(R.id.item_expiry);
        frequency = findViewById(R.id.item_frequency);
        String d1,d2,d3,d4,d5;
        Intent intent = getIntent();
        String s = intent.getStringExtra("position");


        MyDbHandler db = new MyDbHandler(MainActivityViewInfo.this);
        Cursor c = null,c2=null,c3=null,c4=null,cs=null;
        c = db.ret_id(s);
        c2 = db.ret_weight(s);
        c3 = db.ret_expiry(s);
        c4 = db.ret_frequency(s);
        cs = db.ret_all(s);
        if(cs!=null && cs.getCount()>0){
            if(cs.moveToFirst()){

                id.setText("Id of this item is "+cs.getInt(0));
                name.setText("Name of item is "+s);
                weight.setText("Weight of item is "+cs.getString(2));
                expiry.setText("Expiry date of item is "+cs.getString(3));
                frequency.setText("Frequency of item is "+cs.getInt(4));

                cs.close();
            }
        }
//        name.setText("Name of item is "+s);
//        if(c!=null && c.moveToFirst()){
//            id.setText("Id of this item is "+c.getInt(0));
//        }
//        if(c2!=null && c2.moveToFirst()){
//            weight.setText("Weight of item is "+c2.getString(2));
//        }
//        if(c3!=null && c3.moveToFirst()){
//            expiry.setText("Expiry date of item is "+c3.getString(3));
//        }
//        if(c4!=null && c4.moveToFirst()){
//            frequency.setText("Frequency of item is "+c4.getInt(4));
//        }


    }
}
