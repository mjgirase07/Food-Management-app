package com.example.sample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sample.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewItems extends AppCompatActivity {
    ListView listView;

    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_items);

        ArrayList<String> list = new ArrayList();
        listView = findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter =new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);



        MyDbHandler db = new MyDbHandler(MainActivityViewItems.this);
        Item biscuit = new Item();
        biscuit.setItem_id(1);
        biscuit.setItem_name("GoodDay");
        biscuit.setItem_weight("500");
        //biscuit.setItem_expiry("01/01/2022");
        //biscuit.setItem_frequency(3);
        //db.addItem(biscuit);

//        biscuit.setItem_name("Marie");
//        biscuit.setItem_weight("1000");
//        int affectedRows = db.updateItem(biscuit);
//        Log.d("dbharry","No of affected rows are: "+affectedRows);

        Item tea = new Item();
        tea.setItem_id(2);
        tea.setItem_name("Red Label");
        //tea.setItem_weight("1000");
        //db.addItem(tea);

        Item coffee = new Item();
        coffee.setItem_id(3);
        coffee.setItem_name("Nescafe");
        coffee.setItem_weight("800");
        //db.addItem(coffee);

        Item chocolate = new Item();
        chocolate.setItem_id(4);
        chocolate.setItem_name("Dairy Milk");
        chocolate.setItem_weight("550");
        //db.addItem(chocolate);

//        db.deleteContact(2); // Delete contact with id 2

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityViewItems.this);

                builder.setTitle("Delete")
                        .setMessage("Do you want to delete this item ?")
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String Name = listView.getItemAtPosition(index).toString();
                                Cursor c = db.ret_id(Name);
                                if(c!=null && c.moveToFirst()){
                                    //Toast.makeText(MainActivityViewItems.this, "Cursor !!", Toast.LENGTH_SHORT).show();
                                    db.deleteContactById(c.getInt(0));
                                    c.close();
                                }

                                arrayAdapter.notifyDataSetChanged();
                                Log.d("id","id = " + v.getId());
                                List<Item> allItems = db.getAllItems();
                                for(Item item: allItems){

                                  //  Log.d("dbdhruv","Id: " + item.getItem_id() + "\n" + "Name: " + item.getItem_name() + "\n" + "Weight: " + item.getItem_weight() + "\n");
                                 // Log.d("dbdhruv","Name: " + item.getItem_name());
                                    list.add(item.getItem_name());
                                }
                            }
                        }).show();

                AlertDialog dialog = builder.create();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivityViewItems.this,MainActivityViewInfo.class);
                String Name = listView.getItemAtPosition(i).toString();
                intent.putExtra("position",Name.toString());
                setResult(RESULT_OK,intent);
                finish();
                startActivity(intent);
            }
        });

        List<Item> allItems = db.getAllItems();
        for(Item item: allItems){

            Log.d("dbdhruv","Id: " + item.getItem_id() + "\n" + "Name: " + item.getItem_name() + "\n" + "Weight: " + item.getItem_weight() + "\n");
//            Log.d("dbdhruv","Name: " + item.getItem_name());
            list.add(item.getItem_name());
        }
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu,menu);
//
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Type Something");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                arrayAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
}
