package com.example.infopelis.Presenters;

import android.Manifest;
import android.content.Context;

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


}
