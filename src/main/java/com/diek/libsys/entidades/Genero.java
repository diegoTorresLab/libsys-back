package com.diek.libsys.entidades;

import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "generos")
public class Genero {
    @Id
    @Generated(event = EventType.INSERT)
    @Column(name = "id_genero", updatable = false, insertable = false, nullable = false, length = 8)
    private String idGenero;

    @NotEmpty
    private String nombre;
}
