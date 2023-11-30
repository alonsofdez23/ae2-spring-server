package com.libros.ae2springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Anotación que indica que esta es la clase principal de la aplicación
// Spring Boot. Contiene la configuración principal y escanea componentes
// en el mismo paquete y subpaquetes.
@SpringBootApplication
public class Ae2SpringbootApplication {

    public static void main(String[] args) {
        System.out.println("Servicio Rest -> Cargando el contexto de Spring...");
        SpringApplication.run(Ae2SpringbootApplication.class, args);
        System.out.println("Servicio Rest -> Contexto de Spring cargado!");
    }

}
