package com.example.roomify;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBhelper dBhelper ;
    ArrayList<NoteModel> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dBhelper = new DBhelper(this);

        Cursor cursor = dBhelper.getNotes();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                arrayList.add(new NoteModel(cursor.getInt(0), cursor.getString(2), cursor.getString(1)));
            }
        } else {
            Toast.makeText(this, "No notes to display", Toast.LENGTH_SHORT).show();
        }



        NoteAdapter noteAdapter = new NoteAdapter(this,arrayList);
        recyclerView.setAdapter(noteAdapter);
    }
}