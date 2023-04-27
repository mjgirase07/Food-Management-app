package com.example.sample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;

public class MainActivityNotes extends AppCompatActivity {
    FloatingActionButton addNoteBTn;
    RecyclerView recyleview;
    ImageButton menubtn;
    NotesAdapter notesAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_notes);
        recyleview = findViewById(R.id.recyler_view);
        menubtn = findViewById(R.id.menu_btn);
        addNoteBTn = findViewById(R.id.add_note_btn);
        addNoteBTn.setOnClickListener((v)->startActivity(new Intent(MainActivityNotes.this,MainActivityNotesDetails.class)));
        setuprecylerview();
    }

    void setuprecylerview(){

        Query query =Utility.getCollectionReferenceForNotes().orderBy("timestamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query,Note.class).build();
        recyleview.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter = new NotesAdapter(options,this);
        recyleview.setAdapter(notesAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        notesAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        notesAdapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        notesAdapter.notifyDataSetChanged();
    }
}