package com.diek.libsys.servicios.libros;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diek.libsys.entidades.Autor;
import com.diek.libsys.entidades.Editorial;
import com.diek.libsys.entidades.Genero;
import com.diek.libsys.entidades.Libro;
import com.diek.libsys.repositorios.AutorRepositorio;
import com.diek.libsys.repositorios.EditorialRepositorio;
import com.diek.libsys.repositorios.GeneroRepositorio;
import com.diek.libsys.repositorios.LibroRepositorio;

import jakarta.transaction.Transactional;


@Service
public class LibroImplementacionServicio implements LibroServicio{
    @Autowired
    LibroRepositorio libroRepositorio;

    @Autowired
    EditorialRepositorio editorialRepositorio;

    @Autowired
    AutorRepositorio autorRepositorio;

    @Autowired
    GeneroRepositorio generoRepositorio;

    @Override
    @Transactional
    public Libro guardarLibro(Libro libro){
        if(libro.getEditorial() != null && !libro.getEditorial().getIdEditorial().isEmpty()){
            Editorial editorial = editorialRepositorio.findById(libro.getEditorial().getIdEditorial())
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada"));
            libro.setEditorial(editorial);
        }

        if(libro.getAutores() != null && !libro.getAutores().isEmpty()){
            Set<Autor> autoresExistentes = new HashSet<>();
            for (Autor autor: libro.getAutores()){
                autorRepositorio.findById(autor.getIdAutor()).ifPresent(autoresExistentes::add);
            }
            libro.setAutores(autoresExistentes);
        } else {
            throw new RuntimeException("Debe seleccionar al menos un autor");
        }

        if(libro.getGeneros() != null && !libro.getGeneros().isEmpty()){
            Set<Genero> generosExistentes = new HashSet<>();
            for (Genero genero: libro.getGeneros()){
                generoRepositorio.findById(genero.getIdGenero()).ifPresent(generosExistentes::add);
            }
            libro.setGeneros(generosExistentes);
        } else {
            throw new RuntimeException("Debe seleccionar al menos un genero");
        }
        return libroRepositorio.save(libro);
    }

    @Override
    @Transactional
    public Libro actualizarLibro(Libro libro){
        if(libro.getAutores() != null && !libro.getAutores().isEmpty()){
            Set<Autor> autoresExistentes = new HashSet<>();
            for(Autor autor: libro.getAutores()){
                autorRepositorio.findById(autor.getIdAutor()).ifPresent(autoresExistentes::add);
            }
            libro.setAutores(autoresExistentes);
        } else {
            throw new RuntimeException("Debe seleccionar al menos un autor");
        }

        if(libro.getGeneros() != null && !libro.getGeneros().isEmpty()){
            Set<Genero> generosExistentes = new HashSet<>();
            for(Genero genero: libro.getGeneros()){
                generoRepositorio.findById(genero.getIdGenero()).ifPresent(generosExistentes::add);
            }
            libro.setGeneros(generosExistentes);
        } else {
            throw new RuntimeException("Debe seleccionar al menos un geenro");
        }
        return libroRepositorio.save(libro);
    }

    @Override
    public List<Libro> obtenerLibros(){
        return libroRepositorio.findAll();
    }

    @Override
    public Optional<Libro> obtenerLibroPorId(String idLibro){
        return libroRepositorio.findById(idLibro);
    }

    @Override 
    @Transactional
    public void borrarLibro(String idLibro){
        libroRepositorio.deleteById(idLibro);
    }



    @Override 
    @Transactional
    public Libro agregarAutorLibro(String idLibro, String idAutor){
        Libro libro = libroRepositorio.findById(idLibro)
            .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Autor autor = autorRepositorio.findById(idAutor)
            .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        
        libro.getAutores().add(autor);
        return libroRepositorio.save(libro);
    }

    @Override
    @Transactional
    public Libro agregarGeneroLibro(String idLibro, String idGenero){
        Libro libro = libroRepositorio.findById(idLibro)
            .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Genero genero = generoRepositorio.findById(idGenero)
            .orElseThrow(() -> new RuntimeException("Genero no encontrado"));
        libro.getGeneros().add(genero);
        return libroRepositorio.save(libro);
    }



    @Override
    @Transactional
    public Libro borrarAutorLibro(String idLibro, String idAutor){
        Libro libro = libroRepositorio.findById(idLibro)
            .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        
        libro.getAutores().removeIf(autor -> autor.getIdAutor().equals(idAutor));
        return libroRepositorio.save(libro);
    }

    @Override
    @Transactional
    public Libro borrarGeneroLibro(String idLibro, String idGenero){
        Libro libro = libroRepositorio.findById(idLibro)
            .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        
        libro.getGeneros().removeIf(genero -> genero.getIdGenero().equals(idGenero));
        return libroRepositorio.save(libro);
    }
}
