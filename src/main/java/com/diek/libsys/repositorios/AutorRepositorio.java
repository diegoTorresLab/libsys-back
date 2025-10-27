package com.diek.libsys.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diek.libsys.entidades.Autor;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{
    
}
