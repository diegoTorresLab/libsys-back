package com.diek.libsys.dtos;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroDTO {
    private String idLibro;
    private String titulo;
    private String isbn;
    private EditorialDTO editorial;
    private Integer anioPublicacion;
    private Integer numPaginas;
    private String idioma;
    private String descripcion;
    private String tipoMaterial;
    private LocalDateTime fechaRegistro;
    private Set<AutorDTO> autores;
    private Set<GeneroDTO> generos;
}
