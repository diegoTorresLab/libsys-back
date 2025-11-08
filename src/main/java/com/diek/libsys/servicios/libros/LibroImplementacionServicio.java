package com.diek.libsys.servicios.libros;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diek.libsys.dtos.LibroDTO;
import com.diek.libsys.entidades.Autor;
import com.diek.libsys.entidades.Editorial;
import com.diek.libsys.entidades.Genero;
import com.diek.libsys.entidades.Libro;
import com.diek.libsys.mappers.LibroMapper;
import com.diek.libsys.repositorios.AutorRepositorio;
import com.diek.libsys.repositorios.EditorialRepositorio;
import com.diek.libsys.repositorios.GeneroRepositorio;
import com.diek.libsys.repositorios.LibroRepositorio;

import org.springframework.transaction.annotation.Transactional;


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

    @Autowired
    LibroMapper libroMapper;

    @Override
    @Transactional
    public Libro guardarLibro(Libro libro){
        validarEditorial(libro);
        validarRelaciones(libro);
        return libroRepositorio.save(libro);
    }

    @Override
    @Transactional
    public Libro actualizarLibro(Libro libro){
        validarEditorial(libro);
        validarRelaciones(libro);
        return libroRepositorio.save(libro);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LibroDTO> obtenerLibros(){
        List<Libro> libros = libroRepositorio.findAll();
        return libroMapper.toListDTO(libros);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LibroDTO> obtenerLibroPorId(String idLibro){
        return libroRepositorio.findById(idLibro)
            .map(libroMapper::toDTO);
    }

    @Override 
    @Transactional
    public void borrarLibro(String idLibro){
        libroRepositorio.deleteById(idLibro);
    }



    @Override 
    @Transactional
    public LibroDTO agregarAutorLibro(String idLibro, String idAutor){
        Libro libro = validarLibroExistente(idLibro);
        Autor autor = autorRepositorio.findById(idAutor)
            .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        
        libro.getAutores().add(autor);
        Libro libroActualizado = libroRepositorio.save(libro);
        return libroMapper.toDTO(libroActualizado);
    }

    @Override
    @Transactional
    public LibroDTO agregarGeneroLibro(String idLibro, String idGenero){
        Libro libro = validarLibroExistente(idLibro);
        Genero genero = generoRepositorio.findById(idGenero)
            .orElseThrow(() -> new RuntimeException("Genero no encontrado"));
        
        libro.getGeneros().add(genero);
        Libro libroActualizado = libroRepositorio.save(libro);
        return libroMapper.toDTO(libroActualizado);
    }



    @Override
    @Transactional
    public LibroDTO borrarAutorLibro(String idLibro, String idAutor){
        Libro libro = validarLibroExistente(idLibro);
        libro.getAutores().removeIf(autor -> autor.getIdAutor().equals(idAutor));
        Libro libroActualizado = libroRepositorio.save(libro);
        return libroMapper.toDTO(libroActualizado);
    }

    @Override
    @Transactional
    public LibroDTO borrarGeneroLibro(String idLibro, String idGenero){
        Libro libro = validarLibroExistente(idLibro);
        
        libro.getGeneros().removeIf(genero -> genero.getIdGenero().equals(idGenero));
        Libro libroActualizado =  libroRepositorio.save(libro);
        return libroMapper.toDTO(libroActualizado);
    }


    private void validarEditorial(Libro libro){
        if(libro.getEditorial() != null && !libro.getEditorial().getIdEditorial().isEmpty()){
            Editorial editorial = editorialRepositorio.findById(libro.getEditorial().getIdEditorial())
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada"));
            libro.setEditorial(editorial);
        } else {
            throw new RuntimeException("Debe de crear una editorial para el libro");
        }
    }

    private void validarRelaciones(Libro libro){
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
            throw new RuntimeException("Debe seleccionar al menos un genero");
        }
    }

    public Libro validarLibroExistente(String idLibro){
        return libroRepositorio.findById(idLibro)
            .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }
}

