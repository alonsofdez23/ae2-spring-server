package com.libros.ae2springboot.service;

import com.libros.ae2springboot.model.Libro;
import com.libros.ae2springboot.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service: Anotación que indica que esta clase es
// un componente de servicio de Spring.
@Service
public class LibroServiceImp implements LibroService {
    // Se inyecta LibroRepository para acceder a las operaciones CRUD.
    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Libro getLibroById(Long id) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);
        return optionalLibro.orElse(null);
    }

    @Override
    public void addLibro(Libro libro) {
        libroRepository.save(libro);
    }

    @Override
    public void updateLibro(Long id, Libro libro) {
        Optional<Libro> optionalExistingLibro = libroRepository.findById(id);
        if (optionalExistingLibro.isPresent()) {
            Libro existingLibro = optionalExistingLibro.get();
            existingLibro.setTitulo(libro.getTitulo());
            existingLibro.setEditorial(libro.getEditorial());
            existingLibro.setNota(libro.getNota());
            libroRepository.save(existingLibro);
        }
    }

    @Override
    public void deleteLibro(Long id) {
        libroRepository.deleteById(id);
    }
}
