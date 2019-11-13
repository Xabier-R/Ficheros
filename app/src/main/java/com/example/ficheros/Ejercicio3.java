package com.example.ficheros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio3 extends AppCompatActivity {
    private ListView listaWebs;
    private String[] webs= new String[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3);


        listaWebs = findViewById(R.id.listViewWebs);
        AdaptadorWebs adaptadorchat = new AdaptadorWebs(this,webs);
        listaWebs.setAdapter(AdaptadorWebs);
        leerRecurso();


    }

    private void leerRecurso() {
       int i=0;
        try {
            InputStream fraw =getResources().openRawResource(R.raw.recurso_webs);
            BufferedReader brin =new BufferedReader( new InputStreamReader(fraw));
            String linea= brin.readLine();
            while (linea!=null){
                Log.i("Ficheros", linea);
                webs[i]={new Web( linea.)};
                linea=brin.readLine();
                i++;

            }
            fraw.close();

        }catch (Exception ex) {
            Log.e ("Ficheros", "Error al leer fichero desde recurso raw");
        }

    }
}
