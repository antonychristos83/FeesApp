package com.example.feesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Change extends AppCompatActivity {
    EditText e1,e2;
    Button b;
    CDB db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change);
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        b = (Button) findViewById(R.id.bpay);
        db = new CDB(this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=e1.getText().toString();
                String dp,dn;
                dp=e1.getText().toString();
                dn=e2.getText().toString();
                db.update(uname,dp,dn);
                e2.setText("");
                e2.setText("");
            }
        });

    }
    public void onBack(View view){
        finish();
    }
}
