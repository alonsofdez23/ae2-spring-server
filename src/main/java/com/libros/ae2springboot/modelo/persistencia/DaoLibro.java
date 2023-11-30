package com.libros.ae2springboot.modelo.persistencia;

import com.libros.ae2springboot.modelo.entidad.Libro;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DaoLibro {
    public List<Libro> listaLibros;
    public int contador;

    public DaoLibro() {
        System.out.println("DaoLibro -> Creando la lista de libros!");
        listaLibros = new ArrayList<Libro>();
        Libro l1 = new Libro(contador++, "Alas de Sangre", "Planeta", 6.2);
        Libro l2 = new Libro(contador++, "La Biblia", "Mítica", 7.4);
        Libro l3 = new Libro(contador++, "Harry Potter", "Salamandra", 4.5);
        Libro l4 = new Libro(contador++, "El Señor de los Anillos", "Minotauro", 8.9);
        Libro l5 = new Libro(contador++, "El Alquimista", "Oberon", 5.7);
        listaLibros.add(l1);
        listaLibros.add(l2);
        listaLibros.add(l3);
        listaLibros.add(l4);
        listaLibros.add(l5);
    }
}
