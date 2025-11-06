package com.diek.libsys.repositorios;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diek.libsys.entidades.Libro;

import org.springframework.lang.NonNull;


@Repository
public interface LibroRepositorio extends JpaRepository <Libro, String>{
    @NonNull
    @EntityGraph(attributePaths = {"autores", "editorial", "generos"})
    @Override
    List<Libro> findAll();

    @NonNull
    @EntityGraph(attributePaths = {"autores", "editorial", "generos"})
    @Override
    Optional<Libro> findById(@NonNull String idLibro);
}