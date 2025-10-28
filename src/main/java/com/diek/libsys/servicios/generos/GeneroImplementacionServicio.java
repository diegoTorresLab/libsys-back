package com.diek.libsys.servicios.generos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diek.libsys.entidades.Genero;
import com.diek.libsys.repositorios.GeneroRepositorio;

import jakarta.transaction.Transactional;

@Service
public class GeneroImplementacionServicio implements GeneroServicio{
    @Autowired
    GeneroRepositorio generoRepositorio;

    @Override
    @Transactional
    public Genero guardarGenero(Genero genero){
        return generoRepositorio.save(genero);  
    }

    @Override
    public Genero actualizarGenero(Genero genero){
        return generoRepositorio.save(genero);
    }

    @Override
    public List<Genero> obtenerGeneros(){
        return generoRepositorio.findAll();
    }

    @Override
    public Optional<Genero> obtenerGeneroPorId(String idGenero){
        return generoRepositorio.findById(idGenero);
    }

    @Override
    public void borrarGenero(String idGenero){
        generoRepositorio.deleteById(idGenero);
    }
}
