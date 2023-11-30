package com.libros.ae2springboot.controlador;

import com.libros.ae2springboot.modelo.entidad.Libro;
import com.libros.ae2springboot.modelo.persistencia.DaoLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CRUD completo contra la entidad Libros. La BBDD está simulada en memoria.
 */
@RestController
public class ControladorLibro {
    /**
     * En este caso, el objeto de tipo ControladorLibro que hemos dado
     * de alta en el contexto de Spring mediante la anotacion @RestController
     * NECESITA un objeto de tipo DaoLibro para realizar su trabajo (y que
     * dimos de alta previamente con la anotación @Component).
     * Inyectamos dependencias dentro del contexto Spring con la notación @Autowired
     * Cuando se cree este objeto (ControladorLibro) dentro del contexto
     * de Spring, mediante esta anotación le diremos que inyecte un objeto
     * de tipo DaoLibro a la referencia "daoLibro", por lo que el objeto
     * ControladorLibro quedará perfectamente formado.
     */
    @Autowired
    private DaoLibro daoLibro;

    /**
     * POST
     * endpoint: http://localhost:8080/libros
     * Damos de alta un nuevo libro comprobando si ya existe en la lista de libros.
     */
    @PostMapping(path="libros",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> altaLibro(@RequestBody Libro l) {
        System.out.println("altaLibro: objeto libro: " + l);
        if (daoLibro.existeTitulo(l.getTitulo())) {
            return new ResponseEntity<Libro>(l, HttpStatus.CONFLICT); // 409 CONFLICT
        }

        daoLibro.addLibro(l);
        return new ResponseEntity<Libro>(l, HttpStatus.CREATED); // 201 CREATED
    }

    /**
     * DELETE POR ID
     * endpoint: http://localhost:8080/libros/{id}
     * Damos de baja un libro recibiendo como parámetro el ID del libro a dar de baja.
     * En caso de no recibir un ID almacenado en la lista lanza error 404,
     */
    @DeleteMapping(path="libros/{id}")
    public ResponseEntity<Libro> bajaLibro(@PathVariable("id") int id) {
        System.out.println("ID Libro a dar de baja: " + id);
        Libro l = daoLibro.deleteLibro(id);
        if(l != null) {
            return new ResponseEntity<Libro>(l, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND); // 404 NOT FOUND
        }
    }

    /**
     * PUT POR ID
     * endpoint: http://localhost:8080/libros/{id}
     * Modificamos los campos del libro obtenido por el ID.
     * En caso de intentar introducir un título ya existente en la lista lanzará error 409.
     */
    @PutMapping(path="libros/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> modificarlibro(@PathVariable("id") int id, @RequestBody Libro l) {
        if (daoLibro.existeTitulo(l.getTitulo())) {
            System.out.println("Ya existe un libro con el título: " + l.getTitulo());
            return new ResponseEntity<Libro>(l, HttpStatus.CONFLICT); // 409 CONFLICT
        }

        System.out.println("ID a modificar: " + id);
        System.out.println("Datos a modificar: " + l);
        l.setId(id);
        Libro lUpdate = daoLibro.update(l);
        if(lUpdate != null) {
            return new ResponseEntity<Libro>(HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND); // 404 NOT FOUND
        }
    }

    /**
     * GET POR ID
     * endpoint: http://localhost:8080/libros/{id}
     * Obtenemos un libro pasándole un ID.
     * Si no existe un libro con el ID pasado lanza error 404.
     */
    @GetMapping(path="libros/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> getLibro(@PathVariable("id") int id) {
        System.out.println("Buscando libro con id: " + id);
        Libro l = daoLibro.get(id);
        if(l != null) {
            return new ResponseEntity<Libro>(l, HttpStatus.OK); // 200 OK
        }else {
            return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND); // 404 NOT FOUND
        }
    }

    /**
     * GET TODOS LOS LIBROS
     * endpoint: http://localhost:8080/libros
     * Obtiene una lista de todos los libros
     */
    @GetMapping(path="libros", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Libro>> listarLibros() {
        List<Libro> listaLibros = null;
        System.out.println("Listando los libros");
        listaLibros = daoLibro.list();
        System.out.println(listaLibros);
        return new ResponseEntity<List<Libro>>(listaLibros, HttpStatus.OK);  // 200 OK
    }
}
