package com.diek.libsys.servicios.generos;

import java.util.List;
import java.util.Optional;

import com.diek.libsys.entidades.Genero;

public interface GeneroServicio {
    Genero guardarGenero(Genero genero);

    Genero actualizarGenero(Genero genero);

    List<Genero> obtenerGeneros();

    Optional<Genero> obtenerGeneroPorId(String idGenero);

    void borrarGenero(String idGenero);
}
