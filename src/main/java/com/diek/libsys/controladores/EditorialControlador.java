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

import com.diek.libsys.entidades.Editorial;
import com.diek.libsys.servicios.editoriales.EditorialImplementacionServicio;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/editorial")
public class EditorialControlador {
    
    @Autowired
    EditorialImplementacionServicio editorialImplementacionServicio;

    @PostMapping
    public ResponseEntity<Editorial> guardarEditorial(@RequestBody Editorial editorial){
        try{
            Editorial guardarEditorial = editorialImplementacionServicio.guardarEditorial(editorial);
            return new ResponseEntity<>(guardarEditorial, HttpStatus.CREATED);
        } 
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Editorial> actualizarEditorial(@RequestBody Editorial editorial){
        try{
            Editorial actualizarEditorial = editorialImplementacionServicio.actualizarEditorial(editorial);
            return new ResponseEntity<>(actualizarEditorial, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Editorial>> obtenerAutores(){
        return new ResponseEntity<>(editorialImplementacionServicio.obtenerEditoriales(), HttpStatus.OK);
    }

    @GetMapping("/{idEditorial}")
    public ResponseEntity<Editorial> obtenerEditorialPorId(@PathVariable String idEditorial){
        Optional<Editorial> editorial = editorialImplementacionServicio.obtenerEditorialPorId(idEditorial);
        if(editorial.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(editorial.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{idEditorial}")
    public ResponseEntity<Void> borrarEditorial(@PathVariable String idEditorial){
        try {
            Optional<Editorial> editorial = editorialImplementacionServicio.obtenerEditorialPorId(idEditorial);
            if(editorial.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            editorialImplementacionServicio.borrarEditorial(idEditorial);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}