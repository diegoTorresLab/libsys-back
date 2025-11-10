package com.diek.libsys.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.diek.libsys.dtos.EjemplarDTO;
import com.diek.libsys.entidades.Ejemplar;

@Component
public class EjemplarMapper {
    public EjemplarDTO toDTO(Ejemplar ejemplares) {
        if(ejemplares == null){
            return null;
        }

        EjemplarDTO dto = new EjemplarDTO();
        dto.setIdEjemplar(ejemplares.getIdEjemplar());
        dto.setCodigoEjemplar(ejemplares.getCodigoEjemplar());
        dto.setEstadoEjemplar(ejemplares.getEstadoEjemplar());
        dto.setUbicacionFisica(ejemplares.getUbicacionFisica());
        dto.setObservaciones(ejemplares.getObservaciones());

        if(dto.getFechaAdquisicion() != null){
            dto.setFechaAdquisicion(ejemplares.getFechaAdquisicion().toString());
        }

        if(ejemplares.getLibro() != null){
            dto.setIdLibro(ejemplares.getLibro().getIdLibro());
        }   
        return dto;
    }

    public List<EjemplarDTO> toDTOList(List<Ejemplar> ejemplares){
        if(ejemplares == null){
            return null;
        }
        return ejemplares.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
    }
}
