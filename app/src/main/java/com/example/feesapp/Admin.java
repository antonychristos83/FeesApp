package com.example.feesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class Admin extends AppCompatActivity {
    TextView t1,t2;
    Button b,a,p,lg;
    CDB db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        t1 = (TextView) findViewById(R.id.lg);
        t2 = (TextView) findViewById(R.id.lt);
        b = (Button) findViewById(R.id.userlist);
        a = (Button) findViewById(R.id.add);
        p = (Button) findViewById(R.id.pay);
//        lg = (Button) findViewById(R.id.lg);
        db = new CDB(this);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj=new Intent(getApplicationContext(),Login.class);
                startActivity(obj);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CDept> rec = db.getAllvalues();
                String str = "";
                for (CDept cr : rec) {
                    String log = "Id : " + cr.id + ", NAME : " + cr.name + ", COURSE : " + cr.course + ", USERNAME : " + cr.usrname + ", PAYMENT STATUS : " + cr.payment;
                    log = log + "\n";
                    str = str + log;
                }
                t2.setText(str);
            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj;
                obj=new Intent(getApplicationContext(),SignUp.class);
                startActivity(obj);
            }
        });
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj;
                obj=new Intent(getApplicationContext(),Change.class);
                startActivity(obj);
            }
        });
    }
}
