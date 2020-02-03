package com.example.infopelis.Presenters;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.infopelis.Interfaces.formInterface;

public class formPresenter implements formInterface.Presenter{

    private formInterface.View view;

    public formPresenter(formInterface.View view){
        this.view=view;
    }

    @Override
    public void Back() {
        view.returnToList();
    }

    @Override
    public void onClickImage(Context myContext) {
        int ReadPermission = ContextCompat.checkSelfPermission(myContext, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public void resultPermission(int result) {
        if (result == PackageManager.PERMISSION_GRANTED) {
            view.selectImage();
            Log.d("appCrud", "Permiso concecido");
        } else {
            view.showAviso();

            Log.d("appCrud", "Permiso denegado");
        }
    }

    @Override
    public void requirePermision() {

    }


    public void guardarForm(){

    }

    @Override
    public void showAviso() {

    }


}
