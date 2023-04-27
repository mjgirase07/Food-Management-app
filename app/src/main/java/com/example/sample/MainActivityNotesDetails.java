package com.example.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;


public class MainActivityNotesDetails extends AppCompatActivity {

    EditText titleEditText,contentEditText;
    ImageButton saveNoteBtn;
    TextView pageTitleTextView,deleteNoteTextviewBtn;
    String title,content,docId;
    boolean isEditmode;

    @SuppressLint("WrongViewCast")

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
        pageTitleTextView = findViewById(R.id.page_title);
        deleteNoteTextviewBtn = findViewById(R.id.delete_note_textview_btn);

        //receive data
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        docId = getIntent().getStringExtra("docId");

        if(docId!=null && !docId.isEmpty()){
            isEditmode = true;
        }

        titleEditText.setText(title);
        contentEditText.setText(content);

        if(isEditmode){
            pageTitleTextView.setText("Edit Your Note");
            deleteNoteTextviewBtn.setVisibility(View.VISIBLE);
        }

        EditText title = findViewById(R.id.notes_title_text);
        EditText content = findViewById(R.id.notes_content_text);
        ImageButton button = findViewById(R.id.save_note_btn);

        saveNoteBtn.setOnClickListener((v)->saveNote());

        deleteNoteTextviewBtn.setOnClickListener((v)-> deleteNoteFromFirebase());


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
            Note note = new Note();
            note.setTitle(noteTitle);
            note.setContent(noteContent);
            note.setTimestamp(Timestamp.now());

            saveNoteToFirebase(note);
    }

    void saveNoteToFirebase(Note note){
        DocumentReference documentReference;
        if(isEditmode){
            documentReference = Utility.getCollectionReferenceForNotes().document(docId);
        }
        else{
            documentReference = Utility.getCollectionReferenceForNotes().document();
        }


        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(isEditmode){
                    if(task.isSuccessful()){
                        //note added
                        Utility.showToast(MainActivityNotesDetails.this,"Note edited successfully");
                        finish();
                    }else{
                        Utility.showToast(MainActivityNotesDetails.this,"Failed while adding edited note");
                    }
                }
                else {
                    if (task.isSuccessful()) {
                        //note added
                        Utility.showToast(MainActivityNotesDetails.this, "Note added successfully");
                        finish();
                    } else {
                        Utility.showToast(MainActivityNotesDetails.this, "Failed while adding note");
                    }
                }
            }
        });
    }

    void deleteNoteFromFirebase(){
        DocumentReference documentReference;
            documentReference = Utility.getCollectionReferenceForNotes().document(docId);



        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //note deleted
                    Utility.showToast(MainActivityNotesDetails.this,"Note Deleted successfully");
                    finish();
                }else{
                    Utility.showToast(MainActivityNotesDetails.this,"Failed while deleting note");
                }
            }
        });
    }

}