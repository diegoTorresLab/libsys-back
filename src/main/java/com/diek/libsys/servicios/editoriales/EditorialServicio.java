package com.diek.libsys.servicios.editoriales;
import java.util.List;
import java.util.Optional;
import com.diek.libsys.entidades.Editorial;

public interface EditorialServicio {
    Editorial guardarEditorial(Editorial editorial);

    Editorial actualizarEditorial(Editorial editorial);

    List<Editorial> obtenerEditoriales();

    Optional<Editorial> obtenerEditorialPorId(String idEditorial);

    void borrarEditorial(String idEditorial);
}
