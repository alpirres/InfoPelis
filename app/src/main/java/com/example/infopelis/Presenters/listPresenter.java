package com.example.infopelis.Presenters;

import android.content.Context;

import com.example.infopelis.Interfaces.listInterface;
import com.example.infopelis.Models.Pelicula;
import com.example.infopelis.Models.PeliculaModel;

import java.util.ArrayList;

public class listPresenter implements listInterface.Presenter {

    private listInterface.View view;
    public PeliculaModel pelicula;
    private Context myContext;

    public listPresenter(listInterface.View view, Context myContext){

        this.view=view;
        this.myContext=myContext;
        pelicula= PeliculaModel.getInstance(myContext);
    }

    @Override
    public void Add() {
        view.showForm();
    }

    @Override
    public boolean deletePeli(int codigo){
        if(pelicula.delete(codigo)){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public ArrayList<Pelicula> findAllPeliculas(){
        ArrayList<Pelicula> allPelis = pelicula.findAllPelis();
        return allPelis;
    }

    @Override
    public ArrayList<Pelicula> findRecyclerPeliculas(){
        ArrayList<Pelicula> allPelis = pelicula.findSomePelis();
        return allPelis;
    }


    @Override
    public void onClickRecyclerView(Integer id) {

    }
}
