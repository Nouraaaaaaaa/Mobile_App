package com.example.roomify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    EditText username, password ;
    TextView linkRegister;
    Button btnlogin;
    DBhelper dBhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.usernamelogin);
        linkRegister = (TextView) findViewById(R.id.registerLink);
        password = (EditText) findViewById(R.id.Passwordlogin);
        btnlogin = (Button) findViewById(R.id.btnlogin);


        dBhelper = new DBhelper(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if( user.equals("") || pass.equals(""))
                {
                    Toast.makeText(LoginActivity.this,"enter your informations",Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean res = dBhelper.checkPassword(user,pass);
                    if(res == true )
                    {
                        Toast.makeText(LoginActivity.this,"fin dayni",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),NotesActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"User invalid",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}