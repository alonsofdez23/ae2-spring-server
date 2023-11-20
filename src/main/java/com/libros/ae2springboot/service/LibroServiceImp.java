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

    // Se verifica que el libro no tenga un ID asignado
    // y que no exista otro libro con el mismo título
    // antes de guardarlo.
    // Esta comprobación la implemento ya que la pide en el
    // requerimiento 2, pero no es necesario ya que al añadir
    // un nuevo libro, el ID se genera automáticamente de manera
    // autoincremental con la anotación @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public void addLibro(Libro libro) {
        if (libro.getId() == null && !tituloExists(libro.getTitulo())) {
            libroRepository.save(libro);
        }
    }

    // Además de verificar si el libro existe,
    // se verifica si el nuevo título ya está asignado
    // a otro libro.
    @Override
    public void updateLibro(Long id, Libro libro) {
        Optional<Libro> optionalExistingLibro = libroRepository.findById(id);
        if (optionalExistingLibro.isPresent()) {
            Libro existingLibro = optionalExistingLibro.get();
            if (!libro.getTitulo().equals(existingLibro.getTitulo()) && tituloExists(libro.getTitulo())) {
                // Título ya existe para otro libro
                return;
            }
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

    private boolean tituloExists(String titulo) {
        return libroRepository.findByTitulo(titulo).isPresent();
    }
}
