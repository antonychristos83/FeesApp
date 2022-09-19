package com.example.feesapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class UserHome extends AppCompatActivity {
    TextView t1;
    String sp="";
    CDB db;
 TextToSpeech t;
 Button b;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome);
        t1=(TextView)findViewById(R.id.tv);
        b=(Button) findViewById(R.id.txt);
        db=new CDB(this);
        Intent intent = getIntent();
        String str = intent.getStringExtra("key");
        String a[];
        try{
            a=db.getOneDepartment(str);
            if(a[0]!="")
            {
                sp=a[2];
                t1.setText("Name : "+a[0]+" | Course : "+a[1]+" | Payment status : "+a[2]);
                    t=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        public void onInit(int status) {
                            if(status != TextToSpeech.ERROR) {
                                t.setLanguage(Locale.UK);
                            }
                        }
                    });
            }
        }catch (Exception e){
            Log.d("SELECT123",e.toString());
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sp.equalsIgnoreCase("no")){
                    t.speak("Fees Not Paid", TextToSpeech.QUEUE_FLUSH, null);
                    Toast.makeText(getApplicationContext(), "Not Paid", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    t.speak("Fees Paid", TextToSpeech.QUEUE_FLUSH, null);
                    Toast.makeText(getApplicationContext(), "Paid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void onBack(View view){
        finish();
    }
}
