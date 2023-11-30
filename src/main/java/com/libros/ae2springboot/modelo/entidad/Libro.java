package com.libros.ae2springboot.modelo.entidad;

import java.util.Objects;

public class Libro {
    private int id;
    private String titulo;
    private String editorial;
    private double nota;

    public Libro() {
        super();
    }

    public Libro(int id, String titulo, String editorial, double nota) {
        this.id = id;
        this.titulo = titulo;
        this.editorial = editorial;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Libro [id=" + id + ", titulo=" + titulo + ", editorial=" + editorial + ", nota=" + nota + "]";
    }
}
