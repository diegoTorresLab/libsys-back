package com.diek.libsys.servicios.libros;

import java.util.List;
import java.util.Optional;

import com.diek.libsys.dtos.LibroDTO;
import com.diek.libsys.entidades.Libro;

public interface LibroServicio {
    Libro guardarLibro(Libro libro);
    
    Libro actualizarLibro(Libro libro);

    List<LibroDTO> obtenerLibros();

    Optional<LibroDTO> obtenerLibroPorId(String idLibro);
    
    void borrarLibro(String idLibro);

    //Manejar Autores
    LibroDTO agregarAutorLibro(String idLibro, String idAutor);

    LibroDTO borrarAutorLibro(String idLibro, String idAutor);

    //Manejar Generos
    LibroDTO agregarGeneroLibro(String idLibro, String idGenero);

    LibroDTO borrarGeneroLibro(String idLibro, String idGenero);
}
