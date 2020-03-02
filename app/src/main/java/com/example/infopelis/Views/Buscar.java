package com.example.infopelis.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.infopelis.Interfaces.buscarInterface;
import com.example.infopelis.Interfaces.formInterface;
import com.example.infopelis.Presenters.buscarPresenter;
import com.example.infopelis.Presenters.formPresenter;
import com.example.infopelis.R;

import java.util.ArrayList;

public class Buscar extends AppCompatActivity implements buscarInterface.View {

    private buscarInterface.Presenter presenter;
    private Context myContext;
    private ArrayAdapter<String> adapter;
    String TAG = "APPCRUD/BuscarActivity";
    Button buscar;
    EditText titulo;
    Spinner categoria;
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
        myContext=this;
        presenter = new buscarPresenter(this, myContext );

        //inicializacion del array que contiene los items de la categoria
        ArrayList<String> items = new ArrayList<String>();
        items=presenter.obtenerCategorias(myContext);
        items.add(0,"");

        // Definición del Adaptador que contiene la lista de opciones
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,items);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // Definición del Spinner
        categoria =  (Spinner) findViewById(R.id.buscarCat);
        categoria.setAdapter(adapter);

        titulo= (EditText) findViewById(R.id.buscarText);
        fecha= (EditText) findViewById(R.id.buscarFecha);

        buscar= (Button) findViewById(R.id.botonBuscar);
        buscar.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Pulsando boton Buscar...");

                Intent intent = new Intent();
                    if(titulo.length()!=0||categoria.getSelectedItem().toString().length()!=0||fecha.length()!=0) {
                        intent.putExtra("titulo", titulo.getText().toString());
                        intent.putExtra("categoria", categoria.getSelectedItem().toString());
                        intent.putExtra("fecha", fecha.getText().toString());
                    }
                    setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ayuda) {
            Log.d(TAG, "Pulsando menu ayuda...");
            presenter.Help();
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    public void showAyuda() {
        Log.d(TAG, "Lanzando Ayuda");
        Intent intent = new Intent(Buscar.this, Ayuda.class);
        startActivity(intent);
    }
}
