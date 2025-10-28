package com.diek.libsys.servicios.autores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diek.libsys.entidades.Autor;
import com.diek.libsys.repositorios.AutorRepositorio;

import jakarta.transaction.Transactional;

@Service
public class AutorImplementacionServicio implements AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;

    @Override
    @Transactional
    public Autor guardarAutor(Autor autor){
        return autorRepositorio.save(autor);
    }

    @Override
    public Autor actualizarAutor(Autor autor){
        return autorRepositorio.save(autor);
    }

    @Override
    public List<Autor> obtenerAutores(){
        return autorRepositorio.findAll();
    }

    @Override
    public Optional<Autor> obtenerAutorPorId(String idAutor){
        return autorRepositorio.findById(idAutor);
    }

    @Override
    public void borrarAutor(String idAutor){
        autorRepositorio.deleteById(idAutor);
    }    
}
