package com.example.infopelis.Interfaces;

import android.content.Context;

import com.example.infopelis.Models.Pelicula;

public interface formInterface {

    interface Presenter {
        void Back();
        void onClickImage(Context c);

        void resultPermission(int result);
        void requirePermision();

        void showAviso();

        boolean saveDataForm(Pelicula pelicula, Context myContext);
    }

    interface View{
        void returnToList();


        void selectImage();


        void showAviso();
    }
}
