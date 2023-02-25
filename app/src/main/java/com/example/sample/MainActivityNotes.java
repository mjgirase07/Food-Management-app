package com.example.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivityNotes extends AppCompatActivity {
    FloatingActionButton addNoteBTn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_notes);
        addNoteBTn = findViewById(R.id.add_note_btn);
        //addNoteBTn.setOnClickListener((v)-> startActivity(new Intent(MainActivityNotes.this,MainActivityNotesDetails.class)));
    }
}