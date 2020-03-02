package com.example.infopelis.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
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
    static final int PICK_CONTACT_REQUEST = 5;
    String TAG = "APPCRUD/Listado";
    FloatingActionButton fab;
    ImageButton buscar;
    Bundle parametros;
    private ArrayList<Pelicula> pelis;
    private TextView countRow;
    private TextView filtrado;
    private Intent restart;
    private Context myContext;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new listPresenter(this, this);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Pulsando boton flotante...");
                presenter.Add();
            }
        });

        buscar = findViewById(R.id.buscarButton);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Pulsando boton buscar...");
                presenter.Find();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ayuda:
                Log.d(TAG, "Pulsando menu ayuda...");
                presenter.Help();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "Ejecutando onResume...");

        filtrado= findViewById(R.id.Filtros);

        if(parametros !=null){
            filtrado.setText(R.string.filtro);
            System.out.println("-----------/"+parametros.getString("titulo")+"/------------------");
            String titulo = parametros.getString("titulo");
            System.out.println("-----------------:"+titulo+":--------------");
            String cat = parametros.getString("categoria");
            String fecha = parametros.getString("fecha");
            pelis=presenter.doBuscar(titulo,cat,fecha);
            filtrado.setVisibility(View.VISIBLE);
            if(titulo.length()!=0){
                filtrado.append(" Titulo ");
            }
            if(cat.length()!=0){
                filtrado.append(" Categoria ");
            }
            if(fecha.length()!=0){
                filtrado.append(" Fecha ");
            }

        }else{
            pelis=presenter.findRecyclerPeliculas();
            filtrado.setText(R.string.filtro);
            filtrado.setVisibility(View.GONE);
        }

        countRow = (TextView) findViewById(R.id.contactcount);

        myContext=this;


        recycler = findViewById(R.id.recyclerView);
        manager=new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        adapter= new PeliculaAdapter(this, pelis);

        if(pelis.isEmpty()){
            countRow.setText(R.string.countrow);
        }else{
            countRow.setText("Peliculas registradas: "+adapter.getItemCount());
        }



        //SWIPE que para editar pregunta al deslizar el dedo sobre la tarjeta hacia la derecha y borrala si lo deslizamos hacia la izquierda
        ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder,int direction){
                final int idd=pelis.get(viewHolder.getAdapterPosition()).getId();

                //Al deslizar a la izquierda borramos la pregunta
                if(direction == ItemTouchHelper.LEFT){
                    // Recuperación de la vista del AlertDialog a partir del layout de la Actividad
                    LayoutInflater layoutActivity = LayoutInflater.from(myContext);
                    View viewAlertDialog = layoutActivity.inflate(R.layout.alert_dialog_delete, null);

                    // Definición del AlertDialog
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(myContext);

                    // Asignación del AlertDialog a su vista
                    alertDialog.setView(viewAlertDialog);

                    // Recuperación del EditText del AlertDialog
                    final EditText dialogInput = (EditText) viewAlertDialog.findViewById(R.id.dialogInput);

                    // Configuración del AlertDialog
                    alertDialog
                            .setCancelable(false)
                            // Botón Borrar
                            .setPositiveButton(getResources().getString(R.string.borrar),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogBox, int id) {
                                            if(presenter.deletePeli(idd)){
                                                recreate();
                                                Toast.makeText(myContext, R.string.deleteBien , Toast.LENGTH_LONG).show();
                                            }else{
                                                Toast.makeText(myContext, R.string.deleteMal , Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    })
                            // Botón Cancelar
                            .setNegativeButton(getResources().getString(R.string.cancel),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogBox, int id) {
                                            dialogBox.cancel();
                                            recreate();
                                        }
                                    })
                            .create()
                            .show();
                }


                //Al deslizar a la derecha editamos la pregunta
                if(direction == ItemTouchHelper.RIGHT){

                    Intent editarpeli= new Intent(List.this, Form.class);

                    //Creamos la información a pasar entre actividades
                    Bundle b = new Bundle();
                    b.putInt("CodigoPeliculaEdit", idd);


                    //Añadimos la información al intent
                    editarpeli.putExtras(b);

                    startActivity(editarpeli);
                }
            }


        };

        ItemTouchHelper itemTouchHelper =new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recycler);

        recycler.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "Ejecutando Activity Result");
        // Check which request we're responding to
        if (requestCode == PICK_CONTACT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                 parametros=data.getExtras();
            }
        }

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

    @Override
    public void showBuscar() {
        Log.d(TAG, "Lanzando Busqueda");
        Intent intent = new Intent(List.this, Buscar.class);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }

    @Override
    public void showAyuda() {
        Log.d(TAG, "Lanzando Ayuda");
        Intent intent = new Intent(List.this, Ayuda.class);
        startActivity(intent);
    }


}