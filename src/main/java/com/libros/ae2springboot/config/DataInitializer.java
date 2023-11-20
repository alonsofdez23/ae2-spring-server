package com.libros.ae2springboot.config;

import com.libros.ae2springboot.model.Libro;
import com.libros.ae2springboot.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// La clase DataInitializer implementa CommandLineRunner,
// lo que significa que el método run se ejecutará al
// arrancar la aplicación.
@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private LibroRepository libroRepository;

    @Override
    public void run(String... args) throws Exception {
        // Inicializar 5 libros
        inicializarLibros();
    }

    // Crea 5 instancias de Libro con datos predefinidos y
    // los guarda en el repositorio.
    private void inicializarLibros() {
        Libro libro1 = new Libro();
        libro1.setTitulo("Alas de sangre");
        libro1.setEditorial("Planeta");
        libro1.setNota(6.2);

        Libro libro2 = new Libro();
        libro2.setTitulo("La Biblia");
        libro2.setEditorial("Mítica");
        libro2.setNota(7.4);

        Libro libro3 = new Libro();
        libro3.setTitulo("Harry Potter");
        libro3.setEditorial("Salamandra");
        libro3.setNota(4.5);

        Libro libro4 = new Libro();
        libro4.setTitulo("El Señor de los Anillos");
        libro4.setEditorial("Minotauro");
        libro4.setNota(8.9);

        Libro libro5 = new Libro();
        libro5.setTitulo("El Alquimista");
        libro5.setEditorial("Oberon");
        libro5.setNota(5.7);

        // Guardar los libros en el repositorio
        libroRepository.saveAll(Arrays.asList(libro1, libro2, libro3, libro4, libro5));
    }
}
