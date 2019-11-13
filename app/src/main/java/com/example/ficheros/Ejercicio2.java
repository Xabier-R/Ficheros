package com.example.ficheros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ejercicio2 extends AppCompatActivity {
    private Spinner spinner;
    private ArrayList<String> provincias=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        spinner = findViewById(R.id.spinner);


        leerRecurso();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, provincias);
        spinner.setAdapter(adapter);


    }

    private void leerRecurso() {
        try {
            InputStream fraw =getResources().openRawResource(R.raw.recurso_spinner);
            BufferedReader brin =new BufferedReader( new InputStreamReader(fraw));
            String linea= brin.readLine();
            while (linea!=null){
                Log.i("Ficheros", linea);
                provincias.add(linea);
                linea=brin.readLine();

            }
            fraw.close();

        }catch (Exception ex) {
            Log.e ("Ficheros", "Error al leer fichero desde recurso raw");
        }
    }

}

