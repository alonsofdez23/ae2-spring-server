package com.libros.ae2springboot.controller;

import com.libros.ae2springboot.model.Libro;
import com.libros.ae2springboot.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Anotación que indica que esta clase
// es un controlador REST.
@RestController
@RequestMapping("/libros")
public class LibroController {
    // Se inyecta LibroService para acceder a las operaciones CRUD.
    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> getAllLibros() {
        return libroService.getAllLibros();
    }

    @GetMapping("/{id}")
    public Libro getLibroById(@PathVariable Long id) {
        return libroService.getLibroById(id);
    }

    @PostMapping
    public void addLibro(@RequestBody Libro libro) {
        libroService.addLibro(libro);
    }

    @PutMapping("/{id}")
    public void updateLibro(@PathVariable Long id, @RequestBody Libro libro) {
        libroService.updateLibro(id, libro);
    }

    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id) {
        libroService.deleteLibro(id);
    }
}
