package com.example.namrata.hello_world;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Accelerometer extends AppCompatActivity {

    private TextView xValue;
    private TextView yValue;
    private TextView zValue;
    private TextView xminview,xmaxview,yminview,ymaxview,zminview,zmaxview;
    private float xmax,xmin,ymax,ymin,zmax,zmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        xValue = findViewById(R.id.x_value);
        yValue = findViewById(R.id.y_value);
        zValue = findViewById(R.id.z_value);
        xminview = findViewById(R.id.x_value_min);
        yminview = findViewById(R.id.y_value_min);
        zminview = findViewById(R.id.z_value_min);

        xmaxview = findViewById(R.id.x_value_max);
        ymaxview = findViewById(R.id.y_value_max);
        zmaxview = findViewById(R.id.z_value_max);

        xmin=ymin=zmin=Float.MAX_VALUE;
        xmax=ymax=zmax=Float.MIN_VALUE;

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                if(x>xmax) {
                    xmax = x;
                }
                if(x<xmin) {
                    xmin = x;
                }
                if(y>ymax) {
                    ymax = y;
                }
                if(y<ymin) {
                    ymin = y;
                }
                if(z>zmax) {
                    zmax = z;
                }
                if(z<zmin) {
                    zmin = z;
                }
                //String.format ("%.2f", number);

                xValue.setText(String.format ("%.4f", x));
                yValue.setText(String.format ("%.4f", y));
                zValue.setText(String.format ("%.4f", z));

                xminview.setText(String.format ("%.4f", xmin));
                yminview.setText(String.format ("%.4f", ymin));
                zminview.setText(String.format ("%.4f", zmin));

                xmaxview.setText(String.format ("%.4f", xmax));
                ymaxview.setText(String.format ("%.4f", ymax));
                zmaxview.setText(String.format ("%.4f", zmax));


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }

        }, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Accelerometer.this, MainActivity.class);
        Bundle bundle = new Bundle();

//Add your data to bundle
        bundle.putString("login", "true");

//Add the bundle to the intent
        i.putExtras(bundle);
        startActivity(i);
        finish();
    }
}
