package com.diek.libsys.entidades;

import java.time.LocalDate;

import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idEjemplar")
@Entity
@Table(name = "ejemplares")
public class Ejemplar {
    @Id
    @Generated(event = EventType.INSERT)
    @Column(name = "id_ejemplar", updatable = false, insertable = false, nullable = false, length = 8)
    private String idEjemplar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_libro", referencedColumnName = "id_libro")
    @JsonIgnoreProperties({"ejemplares"})
    private Libro libro;

    @Generated(event = EventType.INSERT)
    @Column(name = "codigo_ejemplar", updatable = false, insertable = false, nullable = false, length = 12)
    private String codigoEjemplar;

    @Column(name = "estado_ejemplar")
    @NotEmpty
    private String estadoEjemplar;

    @Column(name = "fecha_adquisicion")
    private LocalDate fechaAdquisicion;

    @NotEmpty
    @Column(name = "ubicacion_fisica")
    private String ubicacionFisica;

    @NotEmpty
    private String observaciones;
}
    