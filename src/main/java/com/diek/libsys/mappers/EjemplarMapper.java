package com.diek.libsys.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.diek.libsys.dtos.EjemplarDTO;
import com.diek.libsys.entidades.Ejemplar;

@Component
public class EjemplarMapper {
    public EjemplarDTO toDTO(Ejemplar ejemplar) {
        if(ejemplar == null){
            return null;
        }

        EjemplarDTO dto = new EjemplarDTO();
        dto.setIdEjemplar(ejemplar.getIdEjemplar());
        dto.setIdLibro(ejemplar.getLibro().getIdLibro());
        dto.setCodigoEjemplar(ejemplar.getCodigoEjemplar());
        dto.setEstadoEjemplar(ejemplar.getEstadoEjemplar());
        dto.setFechaAdquisicion(ejemplar.getFechaAdquisicion().toString());
        dto.setUbicacionFisica(ejemplar.getUbicacionFisica());
        return dto;
    }

    public List<EjemplarDTO> toDTOList(List<Ejemplar> ejemplares){
        return ejemplares.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
    }
}
