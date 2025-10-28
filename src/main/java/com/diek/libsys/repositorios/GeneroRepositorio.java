package com.diek.libsys.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diek.libsys.entidades.Genero;

@Repository
public interface GeneroRepositorio extends JpaRepository<Genero, String>{

}
