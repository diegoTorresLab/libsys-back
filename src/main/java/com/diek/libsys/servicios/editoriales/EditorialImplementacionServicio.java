package com.diek.libsys.servicios.editoriales;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diek.libsys.entidades.Editorial;
import com.diek.libsys.repositorios.EditorialRepositorio;

import jakarta.transaction.Transactional;

@Service
public class EditorialImplementacionServicio implements EditorialServicio{
    @Autowired
    EditorialRepositorio editorialRepositorio;

    @Override
    @Transactional
    public Editorial guardarEditorial(Editorial editorial){
        return editorialRepositorio.save(editorial);
    }

    @Override
    public Editorial actualizarEditorial(Editorial editorial){
        return editorialRepositorio.save(editorial);
    }

    @Override
    public List<Editorial> obtenerEditoriales(){
        return editorialRepositorio.findAll();
    }

    @Override
    public Optional<Editorial> obtenerEditorialPorId(String idEditorial){
        return editorialRepositorio.findById(idEditorial);
    }

    @Override
    public void borrarEditorial(String idEditorial){
        editorialRepositorio.deleteById(idEditorial);
    }
}
