package com.example.infopelis.Interfaces;

import android.content.Context;

public interface formInterface {

    interface Presenter {
        void Back();
        void onClickImage(Context c);

        void resultPermission(int result);
        void requirePermision();

        void showAviso();
    }

    interface View{
        void returnToList();


        void selectImage();


        void showAviso();
    }
}
