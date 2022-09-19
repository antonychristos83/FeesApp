package com.example.feesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button b,t;
    EditText usr,pass;

    CDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        b = (Button) findViewById(R.id.blogin);
        usr = (EditText) findViewById(R.id.lname);
        pass = (EditText) findViewById(R.id.lpass);

        db = new CDB(this);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, passw;
                user = usr.getText().toString();
                passw = pass.getText().toString();
                if (user.equals("") || passw.equals("")) {
                    Toast.makeText(Login.this, "Please Enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkval = db.validity(user, passw);
                    if (checkval == true) {
                        if (user.equals("admin") && passw.equals("admin")) {
                            Intent obj;
                            obj = new Intent(getApplicationContext(), Admin.class);
                            startActivity(obj);
                        } else {
                            Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            Intent obj;
                            obj = new Intent(getApplicationContext(), UserHome.class);
                            obj.putExtra("key", user);
                            startActivity(obj);
                        }
                    } else {
                        Toast.makeText(Login.this, "Invalid Username Password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    public void onSignup(View view){
        Intent obj=new Intent(getApplicationContext(),SignUp.class);
        startActivity(obj);
    }
}