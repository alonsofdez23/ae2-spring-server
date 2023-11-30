package com.libros.ae2springboot.modelo.persistencia;

import com.libros.ae2springboot.modelo.entidad.Libro;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Patron DAO (Data Access Object), objeto que se encarga de hacer las consultas
 * a algún motor de persistencia (BBDD, Ficheros, etc). En este caso vamos a
 * simular que los datos están guardados en una BBDD trabajando con una lista
 * de objetos cargada en memoria.
 * Mediante la anotación @Component, damos de alta un único objeto de esta clase
 * dentro del contexto de Spring.
 */
@Component
public class DaoLibro {
    public List<Libro> listaLibros;
    public int contador;

    /**
     * Cuando se cree el objeto dentro del contexto de Spring, se ejecutará
     * su constructor, que creara los libros y las meterá en una lista
     * para que puedan ser consumidas por nuestros clientes.
     */
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

    /**
     * Introduce un libro al final de la lista
     * @param l Libro que queremos introducir
     */
    public void addLibro(Libro l) {
        l.setId(contador++);
        listaLibros.add(l);
    }

    /**
     * Borramos de la lista el libro con el ID recibido
     * @param id ID del libro a borrar
     * @return devolvemos el libro a borrar
     */
    public Libro deleteLibro(int id) {
        try {
            return listaLibros.remove(id);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("delete -> Libro fuera de rango");
            return null;
        }
    }

    /**
     * Modificamos el libro recibido
     * @param l libro que queremos modificar
     * @return devolvemos el libro ya modificado
     */
    public Libro update(Libro l) {
        try {
            Libro lAux = listaLibros.get(l.getId());
            lAux.setTitulo(l.getTitulo());
            lAux.setEditorial(l.getEditorial());
            lAux.setNota(l.getNota());

            return lAux;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("update -> Libro fuera de rango");
            return null;
        }
    }

    /**
     * Obtenemos el libro con el ID recibido
     * @param id ID del libro a obtener
     * @return devolvemos el libro con el ID obtenido
     */
    public Libro get(int id) {
        try {
            return listaLibros.get(id);
        } catch (IndexOutOfBoundsException e) {
            System.out.print("Libro fuera de rango");
            return null;
        }
    }

    /**
     * Obtenemos la lista de todos los libros
     * @return devolvemos la lista de libros
     */
    public List<Libro> list() {
        return listaLibros;
    }

    /**
     * Comprobamos si ya existe un libro con el título recibido recorriendo
     * toda la lista de libros
     * @param titulo título del libro
     * @return true si el título coincide | false si el título no coincide
     */
    public boolean existeTitulo(String titulo) {
        for (Libro libro : listaLibros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return true;
            }
        }
        return false;
    }
}
