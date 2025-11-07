package com.diek.libsys.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diek.libsys.entidades.Ejemplar;

@Repository
public interface EjemplarRepositorio extends JpaRepository <Ejemplar, String> {

}
