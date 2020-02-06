package com.example.infopelis.Views;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.infopelis.Interfaces.formInterface;
import com.example.infopelis.Models.Pelicula;
import com.example.infopelis.Presenters.formPresenter;
import com.example.infopelis.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Form extends AppCompatActivity implements formInterface.View {

    private formInterface.Presenter presenter;
    String TAG = "APPCRUD/FormularioActivity";
    private Context myContext;
    private ArrayAdapter<String> adapter;
    private Button button;
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    final private int CODE_READ_EXTERNAL_STORAGE_PERMISSION = 123;
    final private int REQUEST_SELECT_IMAGE=201;
    private int editcodigo;
    public ImageButton galeryBoton;
    private ConstraintLayout constraintLayoutMainActivity;
    private static final int PICK_IMAGE = 100;

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
        presenter = new formPresenter(this, myContext);
        pelicula = new Pelicula();


        //inicializacion del array que contiene los items de la categoria
        ArrayList<String> items = new ArrayList<String>();
        items=presenter.obtenerCategorias(myContext);

        // Definición del Adaptador que contiene la lista de opciones
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,items);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // Definición del Spinner
        categoria =  (Spinner) findViewById(R.id.spinner);
        categoria.setAdapter(adapter);

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
                                            categoria.setSelection(adapter.getPosition(dialogInput.getText().toString()));
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


        titulo =  (EditText) findViewById(R.id.tituloEditText);
        duracion =  (EditText) findViewById(R.id.DuracionEditText);
        comentario =  (EditText) findViewById(R.id.comentaeditText);
        director =  (EditText) findViewById(R.id.directorEditText);
        fecha =  (EditText) findViewById(R.id.datepickerText);

        estrenado = (Switch) findViewById(R.id.switch1);
        galeryBoton= findViewById(R.id.galeryBoton);

        errorTitulo = (TextView) findViewById(R.id.titulo_vacio);
        errorComentario = (TextView) findViewById(R.id.comenta_vacio);
        errorCategoria = (TextView) findViewById(R.id.category_vacio);
        errorFecha = (TextView) findViewById(R.id.picker_vacio);
        errorDirector = (TextView) findViewById(R.id.director_vacio);
        errorDururacion = (TextView) findViewById(R.id.dur_vacio);


        if(this.getIntent().getExtras()!=null) {
            Bundle bundle = this.getIntent().getExtras();
            editcodigo = bundle.getInt("CodigoPeliculaEdit");

            Pelicula pelicu = presenter.getPeliculaId(editcodigo,myContext);

            pelicula.setId(pelicu.getId());
            titulo.setText(pelicu.getTitulo());
            duracion.setText(pelicu.getDuracion());
            comentario.setText(pelicu.getComentario());
            director.setText(pelicu.getDirector());
            fecha.setText(pelicu.getFecha());
            categoria.setSelection(pelicu.getCategoria()!=null?items.indexOf(pelicu.getCategoria()):0);
            estrenado.setChecked((pelicu.getEstreno()==null||pelicu.getEstreno()==0)?false:true);
            if(pelicu.getImage() == null){
                galeryBoton.setImageResource(R.drawable.imgdefault);
            }else{
                galeryBoton.setImageBitmap(presenter.pass64toImage(pelicu.getImage()));
            }

        }else{
            editcodigo=-1;
        }


        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Log.d(TAG, "Pulsando boton Guardar...");
                if (validateForm()) {
                    if(editcodigo==-1) {

                        if (presenter.saveDataForm(pelicula, myContext)) {
                            Toast.makeText(myContext, R.string.insertadOK, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(myContext, R.string.insertadKO, Toast.LENGTH_LONG).show();
                        }
                    }else{
                        if(presenter.actualizaPeli(pelicula, myContext)){
                            Toast.makeText(myContext, R.string.updateOK, Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(myContext, R.string.updateOK, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        constraintLayoutMainActivity = findViewById(R.id.coordinatorLayout2);
        galeryBoton= (ImageButton) findViewById(R.id.galeryBoton);
        galeryBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ReadExternalStoragePermission = ContextCompat.checkSelfPermission(myContext, Manifest.permission.READ_EXTERNAL_STORAGE);
                Log.d("MainActivity", "WRITE_EXTERNAL_STORAGE Permission: " + ReadExternalStoragePermission);

                if (ReadExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
                    // Permiso denegado
                    // A partir de Marshmallow (6.0) se pide aceptar o rechazar el permiso en tiempo de ejecución
                    // En las versiones anteriores no es posible hacerlo
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        new AlertDialog.Builder(myContext)
                            .setTitle("Permission needed")
                            .setMessage("Se necesitan permisos para acceder a la galerĂ­a")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityCompat.requestPermissions(Form.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                                }
                            })
                            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Snackbar.make(constraintLayoutMainActivity, getResources().getString(R.string.write_permission_not_accepted), Snackbar.LENGTH_LONG).show();
                                }
                            })
                            .create().show();
                        // Una vez que se pide aceptar o rechazar el permiso se ejecuta el método "onRequestPermissionsResult" para manejar la respuesta
                        // Si el usuario marca "No preguntar más" no se volverá a mostrar este diálogo
                    } else {
                        Snackbar.make(constraintLayoutMainActivity, getResources().getString(R.string.write_permission_denied), Snackbar.LENGTH_LONG)
                                .show();
                    }
                } else {
                    // Permiso aceptado
                    openGallery();
                }

            }
        });

    }


    public void showDatePicker() {
        PickersActivity newFragment = new PickersActivity();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, REQUEST_SELECT_IMAGE);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case (REQUEST_SELECT_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto Bitmap
                    Uri selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();

                    if (selectedPath != null) {
                        // Se leen los bytes de la imagen
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // Se transformam los bytes de la imagen a un Bitmap
                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                        // Se carga el Bitmap en el ImageView
                        ImageView imageView = findViewById(R.id.galeryBoton);
                        imageView.setImageBitmap(bmp);
                    }
                }
                break;
        }
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CODE_READ_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso aceptado
                    Snackbar.make(constraintLayoutMainActivity, getResources().getString(R.string.write_permission_accepted), Snackbar.LENGTH_LONG)
                            .show();
                } else {
                    // Permiso rechazado
                    Snackbar.make(constraintLayoutMainActivity, getResources().getString(R.string.write_permission_not_accepted), Snackbar.LENGTH_LONG)
                            .show();
                }
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

        if(pelicula.setComentario(comentario.getText().toString())){
            errorComentario.setVisibility(View.GONE);
        }else{
            errorComentario.setVisibility(View.VISIBLE);
            esValido=false;
        }

        if (estrenado.isChecked()) {
            pelicula.setEstreno(1);
        } else {
            pelicula.setEstreno(0);
        }

        if(galeryBoton.getDisplay()!=null){
            Bitmap bt = ((BitmapDrawable)galeryBoton.getDrawable()).getBitmap();
            pelicula.setImage(presenter.passImageto64(bt));
        }

        return esValido;
    }
}
