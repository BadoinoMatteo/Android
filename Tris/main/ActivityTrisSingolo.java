package com.badoinomatteo.prova2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTrisSingolo extends AppCompatActivity {
    private static final String TAG= "trisActivitySingolo";
    private TextView textTris;
    private TextView lblTit;
    private Button[][] matrice;
    private int [][] m;
    private Button reset;
    public boolean giocatore=true;
    private int cont=9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tris);
        bindComponents();
        Intent intent;
        intent = getIntent();
        // in stringa -> intent.getStringExtra("g1");
        lblTit.setText(intent.getStringExtra("g1") + " VS" + "COMPUTER");
        inizioGioco();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inizioGioco();
            }
        });

    }

    private void bindComponents(){
        textTris = findViewById(R.id.textViewTris);
        lblTit= findViewById(R.id.lblTit);
        matrice=new Button[3][3];
        matrice[0][0]=findViewById(R.id.button00);  //matrice[0][0].setTransitionName("btn_0_0");
        matrice[0][1]=findViewById(R.id.button01);  //matrice[0][1].setTransitionName("btn_0_1");
        matrice[0][2]=findViewById(R.id.button02);  //matrice[0][2].setTransitionName("btn_0_2");
        matrice[1][0]=findViewById(R.id.button10);  //matrice[1][0].setTransitionName("btn_1_0");
        matrice[1][1]=findViewById(R.id.button11);  //matrice[1][1].setTransitionName("btn_1_1");
        matrice[1][2]=findViewById(R.id.button12);  //matrice[1][2].setTransitionName("btn_1_2");
        matrice[2][0]=findViewById(R.id.button20);  //matrice[2][0].setTransitionName("btn_2_0");
        matrice[2][1]=findViewById(R.id.button21);  //matrice[2][1].setTransitionName("btn_2_1");
        matrice[2][2]=findViewById(R.id.button22);  //matrice[2][2].setTransitionName("btn_2_2");
        reset= findViewById(R.id.buttonReset);
    }



    void vince(String g){
        Toast.makeText(this, g, Toast.LENGTH_LONG).show();
    }

    void bloccapulsanti(){
        for (int i=0;i<3;i++){
            for (int c=0; c<3;c++){
                matrice[i][c].setEnabled(false);
            }
        }
    }


    void inizioGioco(){
        cont=9;

        m= new int[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                m[i][j] = 0;
            }
        }

        for (int i=0;i<3;i++){
            for (int c=0; c<3;c++){
                matrice[i][c].setEnabled(true);
            }
        }
        for (int i=0;i<3;i++){
            for (int c=0; c<3;c++){
                matrice[i][c].setText(R.string.trattino);
                matrice[i][c].setBackgroundResource(R.color.bianco);
            }
        }

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(giocatore){
                    matrice[i][j].setTransitionName("btn_" + i + "_" + j);
                    matrice[i][j].setOnClickListener((new myListener()));
                }else {
                    new myListener();
                }
            }
        }
    }

    void computer() {
        int xComputer=0, yComputer=0;
        xComputer = (int) (Math.random() * 3);
        yComputer = (int) (Math.random() * 3);
        if (cellaVuota(xComputer, yComputer)) {
            m[xComputer][yComputer] = 2;
            matrice[xComputer][yComputer].setText(R.string.giocatore2);
            matrice[xComputer][yComputer].setBackgroundResource(R.color.verde);
            matrice[xComputer][yComputer].setEnabled(false);
        }else {
            computer();
        }
    }

    boolean cellaVuota(int x, int y){
        if(m[x][y]==0){
            return true;
        }else {
            return false;
        }
    }

    class myListener implements View.OnClickListener{
        private static final String TAG="classListener";
        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View v) {
            int x = 0, y = 0;
            boolean vittoria=false;
            Button bL = (Button) v;

            if (giocatore) {
                Log.i(TAG, String.valueOf(bL.getId()));
                x = Integer.parseInt(bL.getTransitionName().split("_")[1]);
                y = Integer.parseInt(bL.getTransitionName().split("_")[2]);
                m[x][y] = 1;
                giocatore = false;
                bL.setText(R.string.giocatore1);
                bL.setBackgroundResource(R.color.rosso);
                bL.setEnabled(false);
            } else {
                computer();
                giocatore = true;
            }
            cont--;


            // stampo matrice
            for(int i=0; i<3; i++){
                Log.d("", String.valueOf(m[i][0]) + " " + String.valueOf(m[i][1]) + " " + String.valueOf(m[i][2]));
            }

            // controllo vittoria
            vittoria=false;

            if(cont<=6){
                //VERTICALE
                if (m[1][y] == m[x][y] && m[0][y] == m[x][y] && m[2][y] == m[x][y]){
                    vittoria=true;   //vittoria verticale
                    Log.d("", "verticale");
                }else{
                    //ORIZZONTALE
                    if (m[x][0] == m[x][y] && m[x][1] == m[x][y] && m[x][2]== m[x][y]){
                        vittoria=true;  //vittoria orizzontale
                        Log.d("", "orizzontale");
                    }else{
                        //DIAGONALE PRINCIPALE
                        if(m[0][0] == m[x][y] && m[1][1] == m[x][y] && m[2][2]== m[x][y]){
                            vittoria=true;  //diagonale principale
                            Log.d("", "principale");
                            //DIAGONALE SECONDARIA
                        }else if(m[0][2]== m[x][y] && m[1][1]==m[x][y] && m[2][0]== m[x][y]){
                            vittoria=true;   // diagonale secondaria
                            Log.d("", "secondaria");
                        }
                    }
                }
            }

            if(vittoria){
                if(!giocatore){ // ho già invertito giocatore 1 con computer
                    Log.d(TAG, "VINCE GIOCATORE 1");
                    vince("VINCE GIOCATORE 1");
                }else{
                    vince("VINCE COMPUTER");
                }
                bloccapulsanti();
            }else if( cont==0){
                Log.d(TAG, "PAREGGIO");
                vince("PAREGGIO");
                bloccapulsanti();
            }
        }
    }
}

