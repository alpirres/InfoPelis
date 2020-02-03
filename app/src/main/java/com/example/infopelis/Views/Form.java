package com.example.infopelis.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.infopelis.Interfaces.formInterface;
import com.example.infopelis.Models.Pelicula;
import com.example.infopelis.Presenters.formPresenter;
import com.example.infopelis.R;

import java.util.ArrayList;

public class Form extends AppCompatActivity implements formInterface.View {

    private formInterface.Presenter presenter;
    String TAG = "APPCRUD/FormularioActivity";
    private Context myContext;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private Button button;
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    final private int CODE_READ_EXTERNAL_STORAGE_PERMISSION = 123;
    public ImageButton galeryBoton;
    private ConstraintLayout constraintLayoutMainActivity;

    public EditText titulo;
    public EditText duracion;
    public EditText comentario;
    public EditText director;
    public EditText fecha;
    public Spinner categoria;
    public Switch estrenado;

    public TextView errorTitulo;
    public TextView errorDururacion;
    public TextView errorCategoria;
    public TextView errorFecha;
    public TextView errorDirector;
    public TextView errorComentario;

    Pelicula pelicula;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
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

        presenter = new formPresenter(this);

        pelicula = new Pelicula();


        titulo =  (EditText) findViewById(R.id.tituloEditText);
        duracion =  (EditText) findViewById(R.id.DuracionEditText);
        comentario =  (EditText) findViewById(R.id.comentaeditText);
        director =  (EditText) findViewById(R.id.directorEditText);
        fecha =  (EditText) findViewById(R.id.datepickerText);
        categoria =  (Spinner) findViewById(R.id.spinner);
        estrenado = (Switch) findViewById(R.id.switch1);

        errorTitulo = (TextView) findViewById(R.id.titulo_vacio);
        errorComentario = (TextView) findViewById(R.id.comenta_vacio);
        errorCategoria = (TextView) findViewById(R.id.category_vacio);
        errorFecha = (TextView) findViewById(R.id.picker_vacio);
        errorDirector = (TextView) findViewById(R.id.director_vacio);
        errorDururacion = (TextView) findViewById(R.id.dur_vacio);



        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Log.d(TAG, "Pulsando boton Guardar...");
                if (validateForm()) {
                    if(presenter.saveDataForm(pelicula, myContext)){
                        Toast.makeText(myContext, R.string.insertadOK , Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(myContext, R.string.insertadKO , Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        galeryBoton= (ImageButton) findViewById(R.id.galeryBoton);
        galeryBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickImage(myContext);
            }
        });

        // Definición del Adaptador que contiene la lista de opciones
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // Definición del Spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        // Definición de la acción del botón del switch
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Recuperación de la vista del AlertDialog a partir del layout de la Actividad
                LayoutInflater layoutActivity = LayoutInflater.from(myContext);
                View viewAlertDialog = layoutActivity.inflate(R.layout.alert_dialog, null);

                // Definición del AlertDialog
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(myContext);

                // Asignación del AlertDialog a su vista
                alertDialog.setView(viewAlertDialog);

                // Recuperación del EditText del AlertDialog
                final EditText dialogInput = (EditText) viewAlertDialog.findViewById(R.id.dialogInput);

                // Configuración del AlertDialog
                alertDialog.setCancelable(false)
                        // Botón Añadir
                        .setPositiveButton(getResources().getString(R.string.add),
                                     new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        if (!dialogInput.getText().toString().equals("")) {
                                            adapter.add(dialogInput.getText().toString());
                                            spinner.setSelection(adapter.getPosition(dialogInput.getText().toString()));
                                        } else {
                                            dialogBox.cancel();
                                            Toast.makeText(myContext, R.string.error_texto_vacio , Toast.LENGTH_LONG).show();
                                        }

                                    }
                                })
                        // Botón Cancelar
                        .setNegativeButton(getResources().getString(R.string.cancel),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                })
                        .create()
                        .show();
            }
        });
    }


    public void showDatePicker() {
        PickersActivity newFragment = new PickersActivity();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
    public void returnToList() {
        Log.d(TAG, "Finalizando la actividad...");
        finish();
    }

    @Override
    public void selectImage() {

    }

    @Override
    public void showAviso() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CODE_READ_EXTERNAL_STORAGE_PERMISSION:
                presenter.resultPermission(grantResults[0]);
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public boolean validateForm() {

        boolean esValido = true;


        if (pelicula.setTitulo(titulo.getText().toString())) {
            errorTitulo.setVisibility(View.GONE);
        } else {
            errorTitulo.setVisibility(View.VISIBLE);
            esValido = false;
        }

        if(pelicula.setFecha(fecha.getText().toString())){
            errorFecha.setVisibility(View.GONE);
        }else{
            errorFecha.setVisibility(View.VISIBLE);
            esValido=false;
        }



        if(categoria.getSelectedItem()!=null && pelicula.setCategoria(categoria.getSelectedItem().toString())){
            errorCategoria.setVisibility(View.GONE);
        }else{
            errorCategoria.setVisibility(View.VISIBLE);
            esValido=false;
        }

        if(pelicula.setDuracion(duracion.getText().toString())){
            errorDururacion.setVisibility(View.GONE);
        }else{
            errorDururacion.setVisibility(View.VISIBLE);
            esValido=false;
        }

        if(pelicula.setDirector(director.getText().toString())){
            errorDirector.setVisibility(View.GONE);
        }else{
            errorDirector.setVisibility(View.VISIBLE);
            esValido=false;
        }

        if(pelicula.setComentario(director.getText().toString())){
            errorComentario.setVisibility(View.GONE);
        }else{
            errorComentario.setVisibility(View.VISIBLE);
            esValido=false;
        }

        return esValido;
    }
}
