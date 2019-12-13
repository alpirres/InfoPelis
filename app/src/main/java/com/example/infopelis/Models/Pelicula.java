package com.example.infopelis.Models;

public class Pelicula{
    private Integer id;
    private String titulo;
    private String duracion;
    private String director;
    private String image;

    public Pelicula() {
    }

    public Pelicula(Integer id, String titulo, String duracion, String director, String image) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.director = director;
        this.image = image;
    }

    public Pelicula(Integer id, String titulo, String duracion, String director) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.director = director;
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
