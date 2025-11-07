package com.diek.libsys.servicios.ejemplares;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diek.libsys.dtos.EjemplarDTO;
import com.diek.libsys.entidades.Ejemplar;
import com.diek.libsys.entidades.Libro;
import com.diek.libsys.mappers.EjemplarMapper;
import com.diek.libsys.repositorios.EjemplarRepositorio;
import com.diek.libsys.repositorios.LibroRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class EjemplarImplementacionServicio implements EjemplarServicio{
    @Autowired
    EjemplarRepositorio ejemplarRepositorio;

    @Autowired
    LibroRepositorio libroRepositorio;

    @Autowired
    EjemplarMapper ejemplarMapper;

    @Override
    @Transactional
    public Ejemplar guardarEjemplar(Ejemplar ejemplar){
        validarEjemplar(ejemplar);
        return ejemplarRepositorio.save(ejemplar);
    }

    @Override
    @Transactional
    public Ejemplar actualizarEjemplar(Ejemplar ejemplar){
        validarEjemplar(ejemplar);
        return ejemplarRepositorio.save(ejemplar);
    }

    private void validarEjemplar(Ejemplar ejemplar){
        if(ejemplar.getLibro() != null && !ejemplar.getLibro().getIdLibro().isEmpty()){
            Libro libro = libroRepositorio.findById(ejemplar.getLibro().getIdLibro())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado")); 
            ejemplar.setLibro(libro);
        }
    }

    
    @Override
    @Transactional
    public List<EjemplarDTO> obtenerEjemplares(){
        List<Ejemplar> ejemplares = ejemplarRepositorio.findAll();
        return ejemplarMapper.toDTOList(ejemplares);
    }

    @Override
    @Transactional
    public Optional<EjemplarDTO> obtenerEjemplarPorId(String idEjemplar){
        return ejemplarRepositorio.findById(idEjemplar)
            .map(ejemplarMapper::toDTO);
    }

    @Override
    public void borrarEjemplar(String idEjemplar){
        ejemplarRepositorio.deleteById(idEjemplar);
    }
}
