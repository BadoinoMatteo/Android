package com.badoinomatteo.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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

public class AccActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "AccActivity";

    private TextView txtX, txtY, txtZ;
    private Button btnEsci;


    /* GESTIONE SENSORE */
    private SensorManager sensorManager; // Oggetto per gestione dei vari sensori
    private Sensor accelerometro; // Oggetto per gestione singolo sensore

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc);

        // Bind Components
        txtX = findViewById(R.id.txtX);
        txtY = findViewById(R.id.txtY);
        txtZ = findViewById(R.id.txtZ);
        btnEsci = findViewById(R.id.btnEsciAcc);
        // Listener
        btnEsci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AccActivity.this.finish(); // Activity "stoppata" ma non "distrutta"
            }
        });

        /* Attivazione e collegamento sensore */
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); // init del Gestore dei sensori
        accelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // specifico che l'oggetto sensore andrà a gestire il sensore Accelerometro

        // Registrazione Listener per sensore specifico
        sensorManager.registerListener((SensorEventListener) this, accelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        // il nostro sensore [accelerometro] ora è attivato, applicando un DELAY_NORMAL ci verranno restituiti dati ogni x secondi

    }


    // Per leggere i dati restituiti dal sensore implemento SensorEventListener all'interno della classe principale AccActivity
    // Questo mi costringe a gestire entrambi i metodi sottostanti [onSensorChanged e onAccuracyChanged]

    // onSensorChanged => [ogni volta che il sensore registra nuovi dati ,{x secondi}, il seguente metodo ci fornisce l'oggetto per poterli leggere
    @Override
    public void onSensorChanged(SensorEvent event) {
        // 1. controllo che il sensore che ha "risvegliato" questo metodo sia, di fatto, l'accelerometro
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            // 2. appurato ciò, mediante l'utilizzo dell'oggetto SensorEvent restituito, leggo i tre dati {3 assi x, y, z} dell'accelerometro
            Log.d(TAG, "X: " + event.values[0] +
                    "Y: "  + event.values[1] +
                    "Z: " + event.values[2]
            );
        }
    }

    // Non modifichiamo la precisione con cui deve lavorare il nostro sensore accelerometro
    // di conseguenza non gestiamo il metodo onAccurancyChanged
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}