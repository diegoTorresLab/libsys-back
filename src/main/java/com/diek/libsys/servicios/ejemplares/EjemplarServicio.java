package com.diek.libsys.servicios.ejemplares;

import java.util.List;
import java.util.Optional;

import com.diek.libsys.dtos.EjemplarDTO;
import com.diek.libsys.entidades.Ejemplar;

public interface EjemplarServicio {
    Ejemplar guardarEjemplar(Ejemplar ejemplar);

    Ejemplar actualizarEjemplar(Ejemplar ejemplar);

    List<EjemplarDTO> obtenerEjemplaresPorLibro(String idLibro);

    Optional <EjemplarDTO> obtenerEjemplarPorId(String idEjemplar);

    void borrarEjemplar(String idEjemplar);
}
