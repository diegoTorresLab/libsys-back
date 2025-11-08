package com.diek.libsys.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EjemplarDTO {
    private String idEjemplar;
    private String idLibro;
    private String codigoEjemplar;
    private String estadoEjemplar;
    private String fechaAdquisicion;
    private String ubicacionFisica;
}
