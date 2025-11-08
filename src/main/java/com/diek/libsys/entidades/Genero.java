package com.diek.libsys.entidades;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"libros"})
@EqualsAndHashCode(of = "idGenero")
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
    @JsonBackReference
    private Set<Libro> libros = new HashSet<>();
}
