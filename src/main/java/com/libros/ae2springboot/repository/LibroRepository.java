package com.libros.ae2springboot.repository;

import com.libros.ae2springboot.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//  Interfaz de repositorio proporcionada por Spring Data JPA
//  para realizar operaciones CRUD en la entidad Libro.
public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Consultas personalizadas si es necesario
    Optional<Libro> findByTitulo(String titulo);
}
