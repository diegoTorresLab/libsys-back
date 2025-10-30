package com.diek.libsys.entidades;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.generator.EventType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "autores")
@NoArgsConstructor
@Entity
public class Autor {
    @Id
    @Generated(event = EventType.INSERT)
    @Column(name = "id_autor", updatable = false, insertable = false, nullable = false, length = 8)
    private String idAutor;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty   
    @Column(name = "pais_origen") 
    private String paisOrigen;
    
    @NotEmpty
    private String biografia;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @ManyToMany(mappedBy = "autores")
    @JsonIgnore
    private Set<Libro> libros = new HashSet<>();
}
