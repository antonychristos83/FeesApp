package com.example.feesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    Button b;
    EditText et1,et2,et3,et4,p,cp;
    CDB db;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        b = (Button) findViewById(R.id.bsignup);
        et1 = (EditText) findViewById(R.id.sname);
        et2 = (EditText) findViewById(R.id.susr);
        et3 = (EditText) findViewById(R.id.scourse);
        et4 = (EditText) findViewById(R.id.scpy);
        p = (EditText) findViewById(R.id.sp);
        cp = (EditText) findViewById(R.id.scp);
        db = new CDB(this);
        t1 = (TextView) findViewById(R.id.stv);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n, c, usr, pass, repass, pay;
                n = et1.getText().toString();
                c = et3.getText().toString();
                usr = et2.getText().toString();
                pass = p.getText().toString();
                repass = cp.getText().toString();
                pay = et4.getText().toString();
                if (usr.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(SignUp.this, "Please Enter all fields..", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(repass)) {
                        Boolean check_user = db.checkusr(usr);
                        if (check_user == false) {
                            db.addSignUp(n, c, usr, pass, pay);
                            et1.setText("");
                            et2.setText("");
                            p.setText("");
                            cp.setText("");
                            Toast.makeText(getApplicationContext(), "Registered Successfully Please Log In", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUp.this, "User Already exists", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(SignUp.this, "Password Not Matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj;
                obj = new Intent(getApplicationContext(), Login.class);
                startActivity(obj);
            }
        });
    }
}
