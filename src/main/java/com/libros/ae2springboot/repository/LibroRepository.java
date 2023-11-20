package com.libros.ae2springboot.repository;

import com.libros.ae2springboot.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Puedes añadir consultas personalizadas si es necesario
}
