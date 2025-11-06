package com.diek.libsys.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorDTO {
    private String idAutor;
    private String nombre;
    private String apellido;
}
