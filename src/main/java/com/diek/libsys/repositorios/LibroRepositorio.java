package com.diek.libsys.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diek.libsys.entidades.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository <Libro, String>{

}
