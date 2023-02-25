package com.example.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sample.model.Item;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivityAddItem extends AppCompatActivity {

    String days(){
        try {
            String CurrentDate= date1.getText().toString();
            String FinalDate= date2.getText().toString();
            Date date1;
            Date date2;
            SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");
            date1 = dates.parse(CurrentDate);
            date2 = dates.parse(FinalDate);
            long difference = Math.abs(date1.getTime() - date2.getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);
            String dayDifference = Long.toString(differenceDates);
            //textView.setText("The difference between two dates is " + dayDifference + " days");
            Toast.makeText(this, "The difference between two dates is " + dayDifference + " days", Toast.LENGTH_SHORT).show();
            return dayDifference;
        } catch (Exception exception) {
            Toast.makeText(this, "Unable to find difference", Toast.LENGTH_SHORT).show();
            return null;
        }

    }
    Button buttonSend;
    EditText name;
    EditText weight;
    EditText id;
    String d1;
    String d2;
    EditText date1;
    EditText date2;
    Integer d3;
    TextView textView;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_add_item);
        buttonSend = findViewById(R.id.button);
        name = findViewById(R.id.name);
        weight = findViewById(R.id.weight);
        id = findViewById(R.id.id);
        date1 = findViewById(R.id.date1);
        date2 = findViewById(R.id.date2);



        MyDbHandler db = new MyDbHandler(MainActivityAddItem.this);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d1 = name.getText().toString();
                d2 = weight.getText().toString();
                d3 = Integer.valueOf(id.getText().toString());

                Item obj = new Item();
                obj.setItem_id(d3);
                obj.setItem_name(d1);
                obj.setItem_weight(d2);
                db.addItem(obj);
                Toast.makeText(MainActivityAddItem.this, days() +"Days left for Expiry", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivityAddItem.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });

    }
}




