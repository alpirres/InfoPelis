package com.example.infopelis.Presenters;

import android.content.Context;

import com.example.infopelis.Interfaces.listInterface;
import com.example.infopelis.Models.ControladorBBDD;
import com.example.infopelis.Models.Pelicula;
import com.example.infopelis.Models.PeliculaModel;

import java.util.ArrayList;

public class listPresenter implements listInterface.Presenter {

    private listInterface.View view;
    private PeliculaModel pelicula;
    private Context myContext;

    public listPresenter(listInterface.View view, Context myContext){

        this.view=view;
        this.myContext=myContext;
        pelicula=new PeliculaModel();
    }

    @Override
    public void Add() {
        view.showForm();
    }

    @Override
    public boolean deletePeli(int codigo){
        ControladorBBDD controladorBBDD = new ControladorBBDD(myContext);
        if(controladorBBDD.delete(codigo)){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public ArrayList<Pelicula> findAllPeliculas(){
        ControladorBBDD controladorBBDD = new ControladorBBDD(myContext);
        ArrayList<Pelicula> allPelis = controladorBBDD.findAllPelis();
        return allPelis;
    }

    @Override
    public void onClickRecyclerView(Integer id) {

    }
}
