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

import com.diek.libsys.entidades.Autor;
import com.diek.libsys.servicios.autores.AutorImplementacionServicio;

@RestController
@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    AutorImplementacionServicio autorImplementacionServicio;

    @PostMapping
    public ResponseEntity<Autor> guardarAutor(@RequestBody Autor autor){
        try{
            Autor guardarAutor = autorImplementacionServicio.guardarAutor(autor);
            return new ResponseEntity<>(guardarAutor, HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Autor> actualizarAutor(@RequestBody Autor autor){
        try{
            Autor actualizarAutor = autorImplementacionServicio.actualizarAutor(autor);
            return new ResponseEntity<>(actualizarAutor,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Autor>> obtenerAutores(){
        return new ResponseEntity<>(autorImplementacionServicio.obtenerAutores(), HttpStatus.OK);
    }

    @GetMapping("/{idAutor}")
    public ResponseEntity<Autor> obtenerAutorPorId(@PathVariable String idAutor){
        Optional<Autor> autor = autorImplementacionServicio.obtenerAutorPorId(idAutor);
        if(autor.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(autor.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{idAutor}")
    public ResponseEntity<Void> borrarAutor(@PathVariable String idAutor){
        try {
            Optional<Autor> autor = autorImplementacionServicio.obtenerAutorPorId(idAutor);
            if(autor.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } 
            autorImplementacionServicio.borrarAutor(idAutor);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
