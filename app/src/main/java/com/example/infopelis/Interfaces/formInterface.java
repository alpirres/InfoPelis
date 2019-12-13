package com.example.infopelis.Interfaces;

import android.content.Context;

public interface formInterface {

    interface Presenter {
        void Back();
        void onClickImage(Context c);
    }

    interface View{
        void returnToList();
        void requirePermision();
    }
}
