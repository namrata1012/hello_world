package com.example.namrata.hello_world;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinTextActivity extends AppCompatActivity {

    private EditText text1,text2;
    private Button mergeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_text);
        mergeBtn = findViewById(R.id.merge);
    }

    public void displayToast(View view) {
        text1 = (EditText) findViewById(R.id.text1);
        String t1 = (String) text1.getText().toString();
        text2 = (EditText) findViewById(R.id.text2);
        String t2 = (String) text2.getText().toString().trim();
        Toast toast1 = Toast.makeText(this, t1+t2 , Toast.LENGTH_LONG);
        toast1.show();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(JoinTextActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();

//Add your data to bundle
        bundle.putString("login", "true");

//Add the bundle to the intent
        i.putExtras(bundle);
        startActivity(i);
        finish();
    }
}
