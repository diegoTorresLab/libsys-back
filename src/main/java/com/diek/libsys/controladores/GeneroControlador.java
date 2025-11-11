package com.diek.libsys.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diek.libsys.entidades.Genero;
import com.diek.libsys.servicios.generos.GeneroImplementacionServicio;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/genero")
public class GeneroControlador {
    @Autowired
    GeneroImplementacionServicio generoImplementacionServicio;

    @PostMapping
    public ResponseEntity<Genero> guardarGenero(@RequestBody Genero genero){
        try {
            Genero guardarGenero = generoImplementacionServicio.guardarGenero(genero);
            return new ResponseEntity<>(guardarGenero, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping 
    public ResponseEntity<Genero> actualizarGenero(@RequestBody Genero genero){
        try {
            Genero actualizarGenero = generoImplementacionServicio.actualizarGenero(genero);
            return new ResponseEntity<>(actualizarGenero, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Genero>> obtenerGeneros(){
        return new ResponseEntity<>(generoImplementacionServicio.obtenerGeneros(), HttpStatus.OK);
    }

    @GetMapping("/{idGenero}")
    public ResponseEntity<Genero> obtenerGeneroPorId(@PathVariable String idGenero){
        Optional<Genero> genero = generoImplementacionServicio.obtenerGeneroPorId(idGenero);
        if(genero.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genero.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{idGenero}")
    public ResponseEntity<Void> borrarGenero(@PathVariable String idGenero){
        try {
            Optional<Genero> genero = generoImplementacionServicio.obtenerGeneroPorId(idGenero);
            if (genero.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            generoImplementacionServicio.borrarGenero(idGenero);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
