package com.badoinomatteo.prova2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Vector;


public class Forza4 extends AppCompatActivity {
    private static final String TAG = "Forza4Activity";
    private final int ROWS = 6;
    private final int COLS = 7;
    private int gioc = 1;

    int m[][];
    Button btn[][];
    Vector<Button> vBtn;

    TableRow tb0, tb1, tb2, tb3, tb4, tb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forza4);

        // init matrix
        m = new int[ROWS][COLS];
        for(int i=0; i<ROWS; i++){
            for(int j=0; j<COLS; j++){
                m[i][j] = 0;
            }
        }
        // init Vector
        vBtn = new Vector<Button>();
        // bind components
        tb0 = findViewById(R.id.tbR0);
        tb1 = findViewById(R.id.tbR1);
        tb2 = findViewById(R.id.tbR2);
        tb3 = findViewById(R.id.tbR3);
        tb4 = findViewById(R.id.tbR4);
        tb5 = findViewById(R.id.tbR5);
        Button btn;
        for(int i=0; i<COLS; i++){
            btn = new Button(this);
            btn.setTransitionName("btn_0_" + i);
            btn.setOnClickListener(new myListener());
            vBtn.add(btn);
            tb0.addView(btn);
        }
        for(int i=0; i<COLS; i++){
            btn = new Button(this);
            btn.setTransitionName("btn_1_" + i);
            btn.setOnClickListener(new myListener());
            vBtn.add(btn);
            tb1.addView(btn);
        }
        for(int i=0; i<COLS; i++){
            btn = new Button(this);
            btn.setTransitionName("btn_2_" + i);
            btn.setOnClickListener(new myListener());
            vBtn.add(btn);
            tb2.addView(btn);
        }
        for(int i=0; i<COLS; i++){
            btn = new Button(this);
            btn.setTransitionName("btn_3_" + i);
            btn.setOnClickListener(new myListener());
            vBtn.add(btn);
            tb3.addView(btn);
        }
        for(int i=0; i<COLS; i++){
            btn = new Button(this);
            btn.setTransitionName("btn_4_" + i);
            btn.setOnClickListener(new myListener());
            vBtn.add(btn);
            tb4.addView(btn);
        }
        for(int i=0; i<COLS; i++){
            btn = new Button(this);
            btn.setTransitionName("btn_5_" + i);
            btn.setOnClickListener(new myListener());
            vBtn.add(btn);
            tb5.addView(btn);
        }
        //bindComponents();
    }

    void vince(String g){
        Toast.makeText(this, g, Toast.LENGTH_LONG).show();
    }

    class myListener implements View.OnClickListener{
        private static final String TAG = "ClassListener";
        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            int x, y;
            x = Integer.parseInt(btn.getTransitionName().split("_")[1]);
            y = Integer.parseInt(btn.getTransitionName().split("_")[2]);
            Log.d(TAG, "x: " + x + " y: " + y);
            inserisciMoneta(x, y);
        }
        private void inserisciMoneta(int x, int y){
            for(int z=0; z<vBtn.size(); z++){
                if(vBtn.get(z).getTransitionName().equals(String.valueOf("btn_"+x+"_"+y))){
                    if(controlloCella(x,y)){
                        m[x][y]=1;
                        vBtn.get(z).setBackgroundResource(R.color.rosso);
                        controlloVincita();
                    }

                }
            }

        }

        private boolean controlloCella(int x, int y){
            if(m[x][y]==0){
                return true;
            }else {
                vince("cella occupata");
                return false;
            }
        }

        private void controlloVincita() {
            boolean vinto = false;
            int y = 0;
            int x = 0;
            int contPedine = 0;
            //CONTROLLO VITTORIA COLONNE
            while (x < COLS && vinto == false) {
                if (m[x][y] == gioc && m[x][y + 1] == gioc) {
                    contPedine++;
                    if(contPedine==4) {
                        vinto = true;
                    }
                }else{
                    contPedine=0;
                }
                x++;
                y = 0;

                return ;
            }

            // CONTROLLO VITTORIA ORIZZONTALE
            x=0;
            y=0;
            contPedine=0;
            while (y < ROWS && !vinto) {
                if (m[x][y] == gioc && m[x+1][y] == gioc){
                    contPedine++;
                    if(contPedine==4) {
                        vinto = true;
                    }
                }else{
                     contPedine=0;
                }
                x=0;
                y ++;
                return;
            }

            //CONTROLLO DIAGONALE PRINCIPALE
            x=0;
            y=0;
            contPedine=0;
            while (y < COLS && !vinto) {
                if (m[x][y] == gioc && m[x+1][y+1] == gioc){
                    contPedine++;
                    if(contPedine==4) {
                        vinto = true;
                    }
                }else{
                    contPedine=0;
                }
                x++;
                y ++;
                return;
            }

            //CONTROLLO DIAGONALE SECONDARIA
            x=0;
            y=6;
            contPedine=0;
            while (y < COLS && !vinto) {
                if (m[x][y] == gioc && m[x+1][y-1] == gioc){
                    contPedine++;
                    if(contPedine==4) {
                        vinto = true;
                    }
                }else{
                    contPedine=0;
                }
                x++;
                y --;
                return;
            }

            if(vinto){
                Log.d(TAG, "VINCE GIOCATORE 1");
                vince("VINCE GIOCATORE 1");
            }
        }
    }
}
