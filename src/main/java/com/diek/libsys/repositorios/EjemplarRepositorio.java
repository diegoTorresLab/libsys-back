package com.diek.libsys.repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diek.libsys.entidades.Ejemplar;

@Repository
public interface EjemplarRepositorio extends JpaRepository <Ejemplar, String> {
    List<Ejemplar> findByLibroIdLibro(String idLibro);
}
