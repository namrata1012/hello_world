package com.example.namrata.hello_world;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mergeBtn, accBtn, gpsBtn, mapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mergeBtn = findViewById(R.id.mergeMainBtn);
        //loginBtn = findViewById(R.id.loginMainBtn);
        accBtn = findViewById(R.id.accMainBtn);
        gpsBtn = findViewById(R.id.gpsMainBtn);
        mapBtn = findViewById(R.id.mapMainBtn);

        Bundle bundle = getIntent().getExtras();
        String isLogin="";
//Extract the data…
        if(bundle != null) {
            isLogin = bundle.getString("login");
        }
        if(!isLogin.equals("true")) {
            Intent mp = new Intent(MainActivity.this, Login2Activity.class);
            startActivity(mp);
            finish();
        }
        mergeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mp = new Intent(MainActivity.this, JoinTextActivity.class);
                startActivity(mp);
                finish();
            }
        });
        /*loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mp = new Intent(MainActivity.this, Login2Activity.class);
                startActivity(mp);
                finish();
            }
        });*/
        accBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mp = new Intent(MainActivity.this, Accelerometer.class);
                startActivity(mp);
                finish();
            }
        });
        gpsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mp = new Intent(MainActivity.this, GPSlocation.class);
                startActivity(mp);
                finish();
            }
        });
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mp = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(mp);
                finish();
            }
        });
    }
/*
    public void displayToast(View view) {
        text1 = (EditText) findViewById(R.id.text1);
        String t1 = (String) text1.getText().toString();
        text2 = (EditText) findViewById(R.id.text2);
        String t2 = (String) text2.getText().toString().trim();
        Toast toast1 = Toast.makeText(this, t1+t2 , Toast.LENGTH_LONG);
        toast1.show();
    }
    */
}
