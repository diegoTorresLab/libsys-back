package com.diek.libsys.mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.diek.libsys.dtos.AutorDTO;
import com.diek.libsys.dtos.EditorialDTO;
import com.diek.libsys.dtos.GeneroDTO;
import com.diek.libsys.dtos.LibroDTO;
import com.diek.libsys.entidades.Libro;

@Component
public class LibroMapper {
    public LibroDTO toDTO(Libro libros){
        if (libros == null) {
            return null;
        }
        LibroDTO dto = new LibroDTO();
        dto.setIdLibro(libros.getIdLibro());
        dto.setTitulo(libros.getTitulo());
        dto.setIsbn(libros.getIsbn());
        dto.setAnioPublicacion(libros.getAnioPublicacion());
        dto.setNumPaginas(libros.getNumPaginas());
        dto.setIdioma(libros.getIdioma());
        dto.setDescripcion(libros.getDescripcion());
        dto.setTipoMaterial(libros.getTipoMaterial());
        dto.setFechaRegistro(libros.getFechaRegistro());

        if(libros.getEditorial() != null){
            EditorialDTO editorialDTO = new EditorialDTO(
                libros.getEditorial().getIdEditorial(),
                libros.getEditorial().getNombre()
            );
            dto.setEditorial(editorialDTO);
        }

        if (libros.getAutores() != null && !libros.getAutores().isEmpty()) {
            Set<AutorDTO> autoresDTO = libros.getAutores().stream()
                .map(autor -> new AutorDTO(
                    autor.getIdAutor(), 
                    autor.getNombre(), 
                    autor.getApellido()
                    ))
                .collect(Collectors.toSet());
            dto.setAutores(autoresDTO);
        }

        if (libros.getGeneros() != null && !libros.getGeneros().isEmpty()){
            Set<GeneroDTO> generoDTO = libros.getGeneros().stream()
                .map(genero -> new GeneroDTO(
                    genero.getIdGenero(),
                    genero.getNombre()
                ))
                .collect(Collectors.toSet());
            dto.setGeneros(generoDTO);
        }
        return dto;
    } 
    
    public List<LibroDTO> toListDTO(List<Libro> libros){
        if (libros == null) {
            return null;
        }
        return libros.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
}
    

