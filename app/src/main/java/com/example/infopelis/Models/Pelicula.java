package com.example.infopelis.Models;

import java.util.regex.Pattern;

public class Pelicula{
    private Integer id;
    private String titulo;
    private String duracion;
    private String director;
    private String fecha;
    private String categoria;
    private Integer estreno;
    private String comentario;
    private String image;


    public Pelicula() {
    }

    public Pelicula(Integer id,String titulo, String director, String image) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.image = image;
    }

    public Pelicula(Integer id, String titulo, String fecha, String categoria, String duracion, String director, Integer estreno, String comentario, String image) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.director = director;
        this.fecha = fecha;
        this.categoria = categoria;
        this.estreno = estreno;
        this.comentario = comentario;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public boolean setId(Integer id) {
        String patern1="^\\d+$";
        if(Pattern.matches(patern1,id.toString())){
            this.id = id;
            return true;
        }else{
            return false;
        }
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

    /**
     * Funcion que valida que el director no este vacio
     * @param director
     * @return
     */
    public boolean setDirector(String director) {
        String patern1="^(?:[A-ZÄËÏÖÜÁÉÍÓÚÂÊÎÔÛÀÈÌÒÙ][a-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙ]+(?:\\s?[A-Za-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙ]+)+)$";
        if(Pattern.matches(patern1,director)){
            this.director = director;
            return true;
        }else{
            return false;
        }

    }

    public String getFecha() {
        return fecha;
    }

    /**
     * Funcion que valida que introduce un formato correcto
     *  dd/mm/aaaa
     * @param fecha
     * @return
     */
    public boolean setFecha(String fecha) {
        String patern1="^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        if(Pattern.matches(patern1,fecha)){
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
        if(!categoria.equals("Añade...")){
            this.categoria = categoria;
            return true;
        }else {
            return false;
        }
    }

    public Integer getEstreno() {
        return estreno;
    }

    public boolean setEstreno(Integer estreno) {
        if( estreno==1||estreno==0 ){
            this.estreno = estreno;
            return true;
        }else{
            return false;
        }
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
