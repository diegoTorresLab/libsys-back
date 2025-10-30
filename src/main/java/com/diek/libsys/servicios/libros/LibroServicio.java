package com.diek.libsys.servicios.libros;

import java.util.List;
import java.util.Optional;

import com.diek.libsys.entidades.Libro;

public interface LibroServicio {
    Libro guardarLibro(Libro libro);
    
    Libro actualizarLibro(Libro libro);

    List<Libro> obtenerLibros();

    Optional<Libro> obtenerLibroPorId(String idLibro);
    
    void borrarLibro(String idLibro);

    //Manejar Autores
    Libro agregarAutorLibro(String idLibro, String idAutor);

    Libro borrarAutorLibro(String idLibro, String idAutor);

    //Manejar Generos
    Libro agregarGeneroLibro(String idLibro, String idGenero);

    Libro borrarGeneroLibro(String idLibro, String idGenero);
}
