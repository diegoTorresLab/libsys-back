package com.diek.libsys.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diek.libsys.dtos.LibroDTO;
import com.diek.libsys.entidades.Libro;
import com.diek.libsys.servicios.libros.LibroImplementacionServicio;

import org.springframework.transaction.annotation.Transactional;
@RestController
@RequestMapping("/libro")
public class LibroControlador {
    @Autowired
    LibroImplementacionServicio libroImplementacionServicio;

    @PostMapping
    public ResponseEntity<Libro> guardarLibro(@RequestBody Libro libro){
        try {
            Libro guardarLibro = libroImplementacionServicio.guardarLibro(libro);
            return new ResponseEntity<>(guardarLibro, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Libro> actualizarLibro(@RequestBody Libro libro){
        try {
            Libro actualizarLibro = libroImplementacionServicio.actualizarLibro(libro);
            return new ResponseEntity<>(actualizarLibro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<LibroDTO>> obtenerLibros(){
        return new ResponseEntity<>(libroImplementacionServicio.obtenerLibros(), HttpStatus.OK);
    }

    @GetMapping("/{idLibro}")
    @Transactional(readOnly = true)
    public ResponseEntity<LibroDTO> obtenerLibroPorId(@PathVariable String idLibro){
        Optional<LibroDTO> libro = libroImplementacionServicio.obtenerLibroPorId(idLibro);
        if(libro.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(libro.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{idLibro}")
    public ResponseEntity<Void> borrarLibro(@PathVariable String idLibro){
        try {
            Optional<LibroDTO> libro = libroImplementacionServicio.obtenerLibroPorId(idLibro);
            if(libro.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            libroImplementacionServicio.borrarLibro(idLibro);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 



    @PutMapping("/{idLibro}/autor/{idAutor}")
    public ResponseEntity<LibroDTO> agregarAutorLibro(@PathVariable String idLibro, @PathVariable String idAutor){
        try {
            LibroDTO libro = libroImplementacionServicio.agregarAutorLibro(idLibro, idAutor);
            return new ResponseEntity<>(libro, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{idLibro}/genero/{idGenero}")
    public ResponseEntity<LibroDTO> agregarGeneroLibro(@PathVariable String idLibro, @PathVariable String idGenero){
        try {
            LibroDTO libro = libroImplementacionServicio.agregarGeneroLibro(idLibro, idGenero);
            return new ResponseEntity<>(libro, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @DeleteMapping("/{idLibro}/autor/{idAutor}")
    public ResponseEntity<LibroDTO> borrarAutorLibro(@PathVariable String idLibro, @PathVariable String idAutor){
        try {
            LibroDTO libro = libroImplementacionServicio.borrarAutorLibro(idLibro, idAutor);
            return new ResponseEntity<>(libro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{idLibro}/genero/{idGenero}")
    public ResponseEntity<LibroDTO> borrarGeneroLibro(@PathVariable String idLibro, @PathVariable String idGenero){
        try {
            LibroDTO libro = libroImplementacionServicio.borrarGeneroLibro(idLibro, idGenero);
            return new ResponseEntity<>(libro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
