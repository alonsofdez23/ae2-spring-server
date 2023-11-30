package com.libros.ae2springboot.controlador;

import com.libros.ae2springboot.modelo.entidad.Libro;
import com.libros.ae2springboot.modelo.persistencia.DaoLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControladorLibro {
    @Autowired
    private DaoLibro daoLibro;

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

    @DeleteMapping(path="libros/{id}")
    public ResponseEntity<Libro> bajaLibro(@PathVariable("id") int id) {
        System.out.println("ID Libro a dar de baja: " + id);
        Libro l = daoLibro.deleteLibro(id);
        if(l != null) {
            return new ResponseEntity<Libro>(l, HttpStatus.OK); // 200 OK
        }else {
            return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND); // 404 NOT FOUND
        }
    }

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
        }else {
            return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND); // 404 NOT FOUND
        }
    }

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

    @GetMapping(path="libros", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Libro>> listarLibros() {
        List<Libro> listaLibros = null;
        System.out.println("Listando los libros");
        listaLibros = daoLibro.list();
        System.out.println(listaLibros);
        return new ResponseEntity<List<Libro>>(listaLibros, HttpStatus.OK);  // 200 OK
    }
}
