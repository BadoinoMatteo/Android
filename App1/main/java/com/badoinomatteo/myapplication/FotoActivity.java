package com.badoinomatteo.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FotoActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "FotoActivity";

    private Button btnEsci;

    private SensorManager sensorManager; // Oggetto per gestione dei vari sensori
    private Sensor fotocamera; // Oggetto per gestione singolo sensore

    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        // Bind Components
        btnEsci = findViewById(R.id.btnEsciAcc);
        // Listener
        btnEsci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FotoActivity.this.finish(); // Activity "stoppata" ma non "distrutta"
            }
        });

        sensorManager = (SensorManager) getSystemService(Context.CAMERA_SERVICE); // init del Gestore dei sensori
        fotocamera = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY); // specifico che l'oggetto sensore andrà a gestire il sensore Accelerometro

        sensorManager.registerListener((SensorEventListener) this, fotocamera, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY){
            Log.
            );
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
