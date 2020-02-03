package com.example.infopelis.Views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infopelis.Interfaces.listInterface;
import com.example.infopelis.Models.Pelicula;
import com.example.infopelis.Presenters.listPresenter;
import com.example.infopelis.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class List extends AppCompatActivity implements listInterface.View {

    private listInterface.Presenter presenter;
    String TAG = "APPCRUD/Listado";
    FloatingActionButton fab;
    private ArrayList<Pelicula> pelis;
    private TextView countRow;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new listPresenter(this);

        pelis=presenter.getAllPeliculas();

        countRow = (TextView) findViewById(R.id.contactcount);


        recycler = findViewById(R.id.recyclerView);
        manager=new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        adapter= new PeliculaAdapter(this, pelis);

        if(!pelis.isEmpty()){
            countRow.setText("Peliculas mostradas: "+adapter.getItemCount());
        }

        recycler.setAdapter(adapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Pulsando boton flotante...");
                presenter.Add();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "Ejecutando onStart...");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
    public void showForm() {
        Log.d(TAG, "Lanzando formulario");
        Intent intent = new Intent(List.this, Form.class);
        startActivity(intent);
    }


}