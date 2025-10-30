package com.diek.libsys.entidades;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

    @ManyToMany(mappedBy = "generos")
    @JsonIgnore
    private Set<Libro> libros = new HashSet<>();
}
