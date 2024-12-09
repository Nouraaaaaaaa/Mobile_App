package com.example.roomify;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoteMianAcivity extends AppCompatActivity {

    EditText titelText, desText;
    DBhelper dBhelper;
    Button AddButton;
    FloatingActionButton floatingActionButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_note_mian_acivity);

        desText = findViewById(R.id.idText);
        titelText = findViewById(R.id.idTitel);
        AddButton = findViewById(R.id.AddBtn);
        floatingActionButton = findViewById(R.id.notbtnshow);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dBhelper = new DBhelper(NoteMianAcivity.this);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (titelText.length() > 0 && desText.length() > 0) {
                    long result = dBhelper.insertNote(
                            titelText.getText().toString(),
                            desText.getText().toString()
                    );

                    if (result != -1) {
                        Toast.makeText(NoteMianAcivity.this, "The Note successfully added", Toast.LENGTH_SHORT).show();
                        titelText.setText("");
                        desText.setText("");
                    } else {
                        Toast.makeText(NoteMianAcivity.this, "The Note failed to add", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(NoteMianAcivity.this, "Please insert your Note or Title", Toast.LENGTH_SHORT).show();
                }
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RecyclerActivity.class);
                startActivity(intent);
            }
        });

    }
}