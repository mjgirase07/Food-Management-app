package com.example.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivityNotesDetails extends AppCompatActivity {

    EditText titleEditText,contentEditText;
    ImageButton saveNoteBtn;
    EditText title = findViewById(R.id.notes_title_text);
    EditText content = findViewById(R.id.notes_content_text);
    @SuppressLint("WrongViewCast")
    Button button = findViewById(R.id.save_note_btn);
    String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_notes_details);

        titleEditText = findViewById(R.id.notes_title_text);
        contentEditText = findViewById(R.id.notes_content_text);
        saveNoteBtn = findViewById(R.id.save_note_btn);

        saveNoteBtn.setOnClickListener((v)->saveNote());

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                s1 = title.getText().toString();
//                s2 = content.getText().toString();
//                SharedPreferences sp = getSharedPreferences("myPrefs",MODE_PRIVATE);
//                SharedPreferences.Editor ed = sp.edit();
//                ed.putString(s1,s2);
//                ed.apply();
//            }
//        });

//        SharedPreferences sp = getSharedPreferences("myPrefs",MODE_PRIVATE);
//        String editVal = sp.getString(s1,s2);

     }

    void saveNote(){
        String noteTitle = titleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();
        if(noteTitle == null || noteTitle.isEmpty()){
            titleEditText.setError("Title is required");
            return;
        }
//        Note note = new Note();
//        note.setTitle(noteTitle);
//        note.setContent(noteContent);


    }

}