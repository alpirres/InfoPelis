package com.example.infopelis.Presenters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.infopelis.Interfaces.formInterface;
import com.example.infopelis.Models.Pelicula;
import com.example.infopelis.Models.PeliculaModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class formPresenter implements formInterface.Presenter{

    private formInterface.View view;
    public PeliculaModel pelicula;

    public formPresenter(formInterface.View view ,Context myContext){
        this.view=view;
        pelicula= PeliculaModel.getInstance(myContext);

    }

    @Override
    public void Back() {
        view.returnToList();
    }

    @Override
    public void Help() {
        view.showAyuda();
    }

    @Override
    public boolean saveDataForm(Pelicula pelicula, Context myContext) {
        if (this.pelicula.insertar(pelicula)) {
            Back();
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean actualizaPeli(Pelicula pelicula, Context myContext) {
        if (this.pelicula.update(pelicula)) {
            Back();
            return true;
        }else{
            return false;
        }

    }

    @Override
    public ArrayList<String> obtenerCategorias(Context myContext){
        ArrayList<String> categorias =new ArrayList<>();
        categorias=pelicula.getCategorias();
        return categorias;
    }

    @Override
    public String selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.choose_picture)),REQUEST_SELECT_IMAGE);
        return "";
    }

    @Override
    public Pelicula getPeliculaId(int id, Context myContext){
        return pelicula.getById(id);
    }

    @Override
    public String passImageto64(Bitmap bm){
        String encodedImage="";
        if(bm!=null) {
            Bitmap resized = Bitmap.createScaledBitmap(bm, 500, 500, true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            resized.compress(Bitmap.CompressFormat.JPEG, 100, baos);//bmisthebitmapobject
            byte[] b = baos.toByteArray();
            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            return encodedImage;
        }else{
            return encodedImage;
        }

    }

    @Override
    public Bitmap pass64toImage(String Imagen){
        byte[] decodedString = Base64.decode(Imagen, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }




}
