package com.badoinomatteo.prova2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
        private Button btnTrisSingolo;
        private Button btnTrisDoppio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindComponents();
        setupEventListener();
    }

    private void bindComponents(){

        btnTrisDoppio = findViewById(R.id.btnTrisDoppio);
        btnTrisSingolo = findViewById(R.id.btnTrisSingolo);

    }


    private void setupEventListener(){
        btnTrisSingolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ActivityTrisSingolo.class);
                intent.putExtra("g1", "Matteo");
                startActivity(intent);
            }
        });
        btnTrisDoppio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ActivityTris.class);
                intent.putExtra("g1", "Matteo");
                intent.putExtra("g2", "Luca");
                startActivity(intent);
            }
        });
    }
}
