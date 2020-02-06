package com.example.infopelis.Views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.infopelis.R;

public class Buscar extends AppCompatActivity {

    String TAG = "APPCRUD/BuscarActivity";
    Button buscar;
    EditText titulo;
    EditText categoria;
    EditText fecha;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Asignar la acción necesaria. En este caso "volver atrás"
                onBackPressed();
            }
        });

        titulo= (EditText) findViewById(R.id.buscarText);
        categoria=(EditText) findViewById(R.id.buscarCat);
        fecha= (EditText) findViewById(R.id.buscarFecha);

        buscar= (Button) findViewById(R.id.botonBuscar);
        buscar.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Pulsando boton Buscar...");

                Intent intent = new Intent(Buscar.this, List.class);
                    if(titulo.length()!=0||categoria.length()!=0||fecha.length()!=0) {
                        intent.putExtra("titulo", titulo.getText().toString());
                        intent.putExtra("categoria", categoria.getText().toString());
                        intent.putExtra("fecha", fecha.getText().toString());
                    }
                startActivity(intent);

                finish();
            }
        });

    }


    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "Ejecutando onStart...");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "Ejecutando onResume...");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "Ejecutando onPause...");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "Ejecutando onStop...");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "Ejecutando onRestart...");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Ejecutando onDestroy...");
    }
}
