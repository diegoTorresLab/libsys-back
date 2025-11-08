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
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"autores", "generos"})
@EqualsAndHashCode(of = "idLibro")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_editorial", referencedColumnName = "id_editorial")
    @JsonIgnoreProperties({"libros"})
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

    @NotEmpty
    @Column(name = "tipo_material")
    private String tipoMaterial;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "libros_autores",
        joinColumns = @JoinColumn(name = "id_libro"),
        inverseJoinColumns = @JoinColumn(name = "id_autor")
    )
    @JsonIgnoreProperties({"libros"})
    private Set<Autor> autores = new HashSet<>();

    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "libros_generos",
        joinColumns = @JoinColumn(name = "id_libro"),
        inverseJoinColumns = @JoinColumn(name = "id_genero")
    )
    @JsonIgnoreProperties({"libros"})
    private Set<Genero> generos = new HashSet<>();



    @OneToMany(mappedBy = "libro")
    @JsonIgnoreProperties("libro-ejemplar")
    private Set<Ejemplar> ejemplares = new HashSet<>();
}
