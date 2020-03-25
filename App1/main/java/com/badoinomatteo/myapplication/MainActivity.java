package com.badoinomatteo.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.badoinomatteo.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView txtSaluta;
    private Button btnSaluta, btnAltobasso, btnAccelerometro, btnFotocamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Collego componenti grafiche
        bindComponents();
        // Collego Listener per gestione eventi
        setupEventListener();
    }
    private void bindComponents(){
        txtSaluta = findViewById(R.id.lblTitolo);
        btnSaluta = findViewById(R.id.btnSaluta);
        btnAltobasso = findViewById(R.id.btnAltobasso);
        btnAccelerometro = findViewById(R.id.btnAccel);
        btnFotocamera = findViewById(R.id.btnFoto);
    }
    private void setupEventListener(){
        btnSaluta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSaluta.setText("Ciao Matteo!!!");
            }
        });
        btnAltobasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AltoBassoActivity.class);
                startActivity(intent);
            }
        });
        btnAccelerometro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccActivity.class);
                startActivity(intent);
            }
        });
        btnFotocamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FotoActivity.class);
                startActivity(intent);
            }
        });
    }
}