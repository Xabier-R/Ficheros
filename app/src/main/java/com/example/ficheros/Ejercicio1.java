package com.example.ficheros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Ejercicio1 extends AppCompatActivity {

private static final int SOLICITUD_PERMISO_WRITE_SD = 0;
    private static final int SOLICITUD_PERMISO_READ_SD = 1;
    private TextView tvContenidoFichero;
    private EditText editTextEscribir;
    private Button btnLeerMemSD, btnEscribirMemSD ,btnBorrarMemSD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);
        tvContenidoFichero = findViewById(R.id.textViewContenido);
        editTextEscribir= findViewById(R.id.editTextEscribir);

        btnEscribirMemSD = findViewById(R.id.BtEscribirFicheroSD);
        btnEscribirMemSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comprobarPermisos()) {
                    Toast.makeText(com.example.ficheros.Ejercicio1.this,
                            "Tenemos permisos para escribir", Toast.LENGTH_SHORT).show();
                    if (sdDisponible()) {
                        escribirEnSD();
                    } else
                        Toast.makeText(com.example.ficheros.Ejercicio1.this,
                                "Tarjeta NO lista para poder escribir", Toast.LENGTH_SHORT).show();
                } else {
                    solicitarPermiso(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            "Sin este permiso no se puede ESCRIBIR en el dispositivo externo",
                            SOLICITUD_PERMISO_WRITE_SD, com.example.ficheros.Ejercicio1.this);
                }
            }
        });
        btnLeerMemSD = findViewById(R.id.BtLeerFicheroSD);
        btnLeerMemSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comprobarPermisos()) {
                    Toast.makeText(com.example.ficheros.Ejercicio1.this,
                            "Tenemos permisos para leer", Toast.LENGTH_SHORT).show();
                    if (sdDisponible()) {
                        leerDeSD();
                    } else
                        Toast.makeText(com.example.ficheros.Ejercicio1.this, "Tarjeta NO lista para poder leer", Toast.LENGTH_SHORT).show();
                } else {
                    solicitarPermiso(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            "Sin este permiso no se puede LEER en el dispositivo externo",
                            SOLICITUD_PERMISO_WRITE_SD, com.example.ficheros.Ejercicio1.this);

                }
            }
        });
        btnBorrarMemSD=findViewById(R.id.BtBorrarFichSD);
        btnBorrarMemSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comprobarPermisos()) {
                    Toast.makeText(com.example.ficheros.Ejercicio1.this,
                            "Tenemos permisos para borrar", Toast.LENGTH_SHORT).show();
                    if (sdDisponible()) {
                        borrarEnSD();
                    } else
                        Toast.makeText(com.example.ficheros.Ejercicio1.this, "Tarjeta NO lista para poder borrar", Toast.LENGTH_SHORT).show();
                } else {
                    solicitarPermiso(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            "Sin este permiso no se puede LEER en el dispositivo externo",
                            SOLICITUD_PERMISO_WRITE_SD, com.example.ficheros.Ejercicio1.this);

                }
            }
        });
    }
    public void escribirFichero(View view) {
        try{
            OutputStreamWriter osw=new OutputStreamWriter(
                    openFileOutput("prueba_int.txt", Context.MODE_PRIVATE));
            osw.write(editTextEscribir.getText().toString()+"\n");
            Log.i ("Ficheros", "Escribiendo en fichero de memoria interna");
            osw.close();
        }catch (Exception e) {
            Log.e ("Ficheros","ERROR!! al escribir fichero a memoria interna");
        }
    }

    public void borrarrFichero(View view) {
        try{
            OutputStreamWriter osw=new OutputStreamWriter(
                    openFileOutput("prueba_int.txt", Context.MODE_PRIVATE));
            osw.write("");
            Log.i ("Ficheros", "borrando en fichero de memoria interna");
            osw.close();
        }catch (Exception e) {
            Log.e ("Ficheros","ERROR!! al borrar fichero a memoria interna");
        }
    }

    public void leerFichero (View view){
        try{
            BufferedReader fin =new BufferedReader(
                    new InputStreamReader(openFileInput("prueba_int.txt")));
            String texto="";
            String linea= fin.readLine();
            while (linea!=null){
                texto=texto+linea+"\n";
                Log.i("Ficheros", linea);
                linea=fin.readLine();
            }
            fin.close();
            tvContenidoFichero.setText(texto);
        }catch (Exception ex){
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }
    }

    private boolean comprobarPermisos (){
        //Comprobamos que tenemos permiso para realizar la operación.
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean sdDisponible () {
        boolean sdDisponible = false;
        boolean sdAccesoEscritura = false;
        //Comprobamos el estado de la memoria externa
        String estado = Environment.getExternalStorageState();
        Log.i("Memoria", estado);
        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            sdDisponible = true;
            sdAccesoEscritura = true;
        } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            sdDisponible = true;
            sdAccesoEscritura = false;
        } else {
            sdDisponible = false;
            sdAccesoEscritura = false;
        }
        if (sdDisponible) Toast.makeText(getApplicationContext(),
                "Tengo Tarjeta SD", Toast.LENGTH_SHORT).show();

        if (sdAccesoEscritura) Toast.makeText(getApplicationContext(),
                "La tarjeta SD es escribible", Toast.LENGTH_SHORT).show();

        if (sdDisponible && sdAccesoEscritura)
            return true;
        else
            return false;
    }


    private void escribirEnSD (){
        try {
            File ruta_sd = Environment.getExternalStorageDirectory();
            File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f));
            osw.write(editTextEscribir.getText().toString()+"\n");
            osw.close();Log.i("Ficheros", "fichero escrito correctamente");
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero en tarjeta SD");
        }
    }

    private void borrarEnSD (){
        try {
            File ruta_sd = Environment.getExternalStorageDirectory();
            File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f));
            osw.write("");

            osw.close();Log.i("Ficheros", "fichero borrado correctamente");
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al borrar fichero en tarjeta SD");
        }
    }

    private void leerDeSD(){
        try {File ruta_sd = Environment.getExternalStorageDirectory();
            File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String texto = "";String linea = br.readLine();
            while (linea != null) {texto = texto + linea + "\n";
                linea = br.readLine();}br.close();Log.i("Ficheros", texto);
            tvContenidoFichero.setText(texto);
        } catch (Exception ex) {Log.e("Ficheros", "ERROR!! en la lectura del fichero en SD");
        }
    }

    public void leerRecurso(View view){
        try {
            InputStream fraw =getResources().openRawResource(R.raw.prueba_raw);
            BufferedReader brin =new BufferedReader( new InputStreamReader(fraw));
            String texto="";
            String linea= brin.readLine();
            while (linea!=null){
                texto=texto+linea+"\n";
                Log.i("Ficheros", linea);
                linea=brin.readLine();
            }
            fraw.close();
            tvContenidoFichero.setText(texto);
        }catch (Exception ex) {
            Log.e ("Ficheros", "Error al leer fichero desde recurso raw");
        }
    }

    private static void solicitarPermiso (final String permiso,
                                          String justificacion,
                                          final int requestCode,
                                          final Activity actividad){

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                actividad, permiso)) {
            //Informamos al usuario para qué y por qué
            // se necesitan los permisos
            new AlertDialog.Builder(actividad).setTitle("Solicitud de permiso")
                    .setMessage(justificacion).setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(actividad,
                                    new String[]{permiso}, requestCode);
                        }
                    }).show();
        }
        else {
            //Muestra el cuadro de dialogo para la solicitud de permisos y
            // registra el permiso según respuesta del usuario
            ActivityCompat.requestPermissions(actividad,
                    new String[]{permiso}, requestCode);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == SOLICITUD_PERMISO_WRITE_SD){
            if (grantResults.length >=1 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Log.e("AAAA", "Escribir Memoria SD");
                sdDisponible();
                escribirEnSD();
            }
            else {
                Toast.makeText(this,"No se puede ESCRIBIR en memoria SD",
                        Toast.LENGTH_LONG ).show();
            }
        }
        else if (requestCode == SOLICITUD_PERMISO_READ_SD){
            if (grantResults.length == 1 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                leerDeSD();
            }
            else {
                Toast.makeText(this,"No se puede LEER de memoria SD",
                        Toast.LENGTH_LONG ).show();
            }
        }
    }
}