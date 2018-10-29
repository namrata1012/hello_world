package com.example.namrata.hello_world;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {

    EditText email,password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = new Intent(context, Accelerometer.class);
        startActivity(intent);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        login = (Button)findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("admin") &&
                        password.getText().toString().equals("admin")) {

                    Intent intent = new Intent(context, Accelerometer.class);
                    startActivity(intent);
                }else{

                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(context);
                    builder.setTitle("Something went wrong")
                            .setMessage("Retry ?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            }).setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ActivityLogin.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
