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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.diek.libsys.dtos.EjemplarDTO;
import com.diek.libsys.entidades.Ejemplar;
import com.diek.libsys.entidades.Libro;
import com.diek.libsys.servicios.ejemplares.EjemplarImplementacionServicio;


@RestController
@RequestMapping("/libro/{idLibro}/ejemplar")
public class EjemplarControlador {
    @Autowired
    EjemplarImplementacionServicio ejemplarImplementacionServicio;

    @PostMapping
    public ResponseEntity<Ejemplar> guardarEjemplar(
        @PathVariable String idLibro, 
        @RequestBody Ejemplar ejemplar){
        try {
            ejemplar.setLibro(new Libro());
            ejemplar.getLibro().setIdLibro(idLibro);
            Ejemplar guardarEjemplar = ejemplarImplementacionServicio.guardarEjemplar(ejemplar);
            return new ResponseEntity<>(guardarEjemplar, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{idEjemplar}")
    public ResponseEntity<Ejemplar> actualizarEjemplar(
        @PathVariable String idLibro, 
        @RequestBody Ejemplar ejemplar, 
        @PathVariable String idEjemplar){
        try {
            ejemplar.setIdEjemplar(idEjemplar);
            ejemplar.setLibro(new Libro());
            ejemplar.getLibro().setIdLibro(idLibro);
            Ejemplar actualizarEjemplar = ejemplarImplementacionServicio.actualizarEjemplar(ejemplar);
            return new ResponseEntity<>(actualizarEjemplar, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<EjemplarDTO>> obtenerEjemplaresPorLibro(@PathVariable String idLibro){
        List<EjemplarDTO> ejemplares = ejemplarImplementacionServicio.obtenerEjemplaresPorLibro(idLibro);
        return new ResponseEntity<>(ejemplares, HttpStatus.OK);
    }

    @GetMapping("/{idEjemplar}")
    public ResponseEntity<EjemplarDTO> obternerEjemplarPorId(@PathVariable String idEjemplar){
        Optional<EjemplarDTO> ejemplar = ejemplarImplementacionServicio.obtenerEjemplarPorId(idEjemplar);
        if(ejemplar.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ejemplar.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{idEjemplar}")
    public ResponseEntity<Void> borrarEjemplar(@PathVariable String idEjemplar){
        try {
            ejemplarImplementacionServicio.borrarEjemplar(idEjemplar);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
