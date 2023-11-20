package com.libros.ae2springboot.service;

import com.libros.ae2springboot.model.Libro;

import java.util.List;

// Interfaz que define operaciones CRUD para la entidad "Libro".
public interface LibroService {
    List<Libro> getAllLibros();

    Libro getLibroById(Long id);

    void addLibro(Libro libro);

    void updateLibro(Long id, Libro libro);

    void deleteLibro(Long id);
}
