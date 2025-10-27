package com.diek.libsys.servicios.autores;
import java.util.List;
import java.util.Optional;

import com.diek.libsys.entidades.Autor;

public interface AutorServicio {
    Autor guardarAutor(Autor autor);

    Autor actualizarAutor(Autor autor);

    List<Autor> obtenerAutores();

    Optional<Autor> obtenerAutorPorId(String idAutor);

    void borrarAutor(String idAutor);
}
