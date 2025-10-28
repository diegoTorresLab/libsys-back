package com.diek.libsys.entidades;

import java.time.LocalDateTime;

import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "editoriales")
public class Editorial {
    @Id
    @Generated(event = EventType.INSERT)
    @Column(name = "id_editorial", updatable = false, insertable = false, nullable = false, length = 8)
    private String idEditorial;

    @NotEmpty
    private String nombre;

    @NotEmpty
    @Column(name = "pais_origen")
    private String paisOrigen;

    @NotBlank
    @Column(name = "sitio_web")
    private String sitioWeb;

    @Email
    @NotBlank
    private String email;

    private String telefono;

    @Column(name = "fecha_registro", updatable = false, insertable = false)
    private LocalDateTime fechaRegistro;
}
