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

public class ActivityTris extends AppCompatActivity {
    private static final String TAG= "trisActivity";
    private TextView textTris;
    private TextView lblTit;
    private Button[][] matrice;
    private int [][] m;
    private Button reset;
    public boolean giocatore=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tris);
        bindComponents();
        Intent intent;
        intent = getIntent();
        // in stringa -> intent.getStringExtra("g1");
        lblTit.setText(intent.getStringExtra("g1") + " VS " + intent.getStringExtra("g2"));
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
        m= new int[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                m[i][j] = 0;
            }
        }

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                matrice[i][j].setTransitionName("btn_" + i + "_" + j);
                matrice[i][j].setOnClickListener(new myListener());
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

    }

    class myListener implements View.OnClickListener{
        private static final String TAG="classListener";
        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View v) {
            int x, y;
            boolean vittoria;
            Button bL= (Button) v;
            Log.i(TAG, String.valueOf(bL.getId()));
            x = Integer.parseInt(bL.getTransitionName().split("_")[1]);
            y = Integer.parseInt(bL.getTransitionName().split("_")[2]);

            if(giocatore){
                m[x][y]=1;
                giocatore=false;
                bL.setText(R.string.giocatore1);
                bL.setBackgroundResource(R.color.rosso);
            }else{
                m[x][y]=2;
                giocatore=true;
                bL.setText(R.string.giocatore2);
                bL.setBackgroundResource(R.color.verde);
            }
            bL.setEnabled(false);

            // stampo matrice
            for(int i=0; i<3; i++){
                Log.d("", String.valueOf(m[i][0]) + " " + String.valueOf(m[i][1]) + " " + String.valueOf(m[i][2]));
            }

            // controllo vittoria
            vittoria=false;

            //VERTICALE
            if (m[1][y] == m[x][y] && m[0][y] == m[x][y] && m[2][y] == m[x][y]){
                vittoria=true;   //vittoria verticale
            }else{
                //ORIZZONTALE
                if (m[x][0] == m[x][y] && m[x][1] == m[x][y] && m[x][2]== m[x][y]){
                    vittoria=true;  //vittoria orizzontale
                }else{
                    //DIAGONALE PRINCIPALE
                    if(m[0][0] == m[x][y] && m[1][1] == m[x][y] && m[2][2]== m[x][y]){
                        vittoria=true;  //diagonale principale
                        //DIAGONALE SECONDARIA
                    }else if(m[0][2]== m[x][y] && m[1][1]==m[x][y] && m[2][0]== m[x][y]){
                        vittoria=true;   // diagonale secondaria
                    }
                }
            }
            if(vittoria){
                if(!giocatore){ // ho già invertito giocatore 1 con giocatore 2
                    Log.d(TAG, "VINCE GIOCATORE 1");
                    vince("VINCE GIOCATORE 1");
                }else{
                    Log.d(TAG, "VINCE GIOCATORE 2");
                    vince("VINCE GIOCATORE 2");
                }
                bloccapulsanti();
            }
        }
    }
}
