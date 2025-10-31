package com.diek.libsys.entidades;

import java.time.LocalDateTime;

import java.util.HashSet;

import java.util.Set;

import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "libros")
public class Libro {
    @Id
    @Generated(event = EventType.INSERT)
    @Column(name = "id_libro", insertable = false, updatable = false, nullable = false, length = 8)
    private String idLibro;

    @NotEmpty
    private String titulo;

    @NotEmpty
    @Column(unique = true)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "id_editorial", referencedColumnName = "id_editorial")
    @JsonIgnoreProperties({"libros", "fechaRegistro"})
    private Editorial editorial;

    @NotNull
    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;

    @NotNull
    @Column(name = "num_paginas")
    private Integer numPaginas;

    @NotEmpty
    private String idioma;

    @NotEmpty
    private String descripcion;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "libros_autores",
        joinColumns = @JoinColumn(name = "id_libro"),
        inverseJoinColumns = @JoinColumn(name = "id_autor")
    )
    @JsonIgnoreProperties({"libros", "paisOrigen", "biografia", "fechaRegistro"})
    private Set<Autor> autores = new HashSet<>();


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "libros_generos",
        joinColumns = @JoinColumn(name = "id_libro"),
        inverseJoinColumns = @JoinColumn(name = "id_genero")
    )
    @JsonIgnoreProperties({"libros", "fechaRegistro"})
    private Set<Genero> generos = new HashSet<>();
}
