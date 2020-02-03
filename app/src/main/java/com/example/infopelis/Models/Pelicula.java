package com.example.infopelis.Models;

import java.util.regex.Pattern;

public class Pelicula{
    private Integer id;
    private String titulo;
    private String duracion;
    private String director;
    private String fecha;
    private String categoria;
    private String estreno;
    private String comentario;
    private String image;


    public Pelicula() {
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }


    /**
     * funcion que valida que el titulo no esté vacio
     * @param titulo
     * @return boolean
     */
    public boolean setTitulo(String titulo) {
        if(!titulo.equals("")){
            this.titulo = titulo;
            return true;
        }else {
            return false;
        }

    }

    public String getDuracion() {
        return duracion;
    }

    /**
     * función que valida una duracion entre 1 y 500
     * @param duracion
     * @return boolean
     */
    public boolean setDuracion(String duracion) {
        String patern1="^[1-4]?(\\d{1,2})$";
        if(Pattern.matches(patern1,duracion)){
            this.duracion = duracion;
            return true;
        }else{
            return false;
        }

    }

    public String getDirector() {
        return director;
    }

    public boolean setDirector(String director) {
        if(director.length()!=0){
            this.director = director;
            return true;
        }else{
            return false;
        }

    }

    public String getFecha() {
        return fecha;
    }

    public boolean setFecha(String fecha) {
        if(fecha.length()!=0){
            this.fecha = fecha;
            return true;
        }else{
            return false;
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean setCategoria(String categoria) {
        if(categoria.length()!=0){
            this.categoria = categoria;
            return true;
        }else {
            return false;
        }
    }

    public String getEstreno() {
        return estreno;
    }

    public void setEstreno(String estreno) {
        this.estreno = estreno;
    }

    public String getComentario() {
        return comentario;
    }

    public boolean setComentario(String comentario) {
        if(comentario.length()!=0){
            this.comentario = comentario;
            return true;
        }else{
            return false;
        }

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
            this.image = image;
    }

}
