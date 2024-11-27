package com.aluracurso.one_foro.domain.topico.Validaciones;

import com.aluracurso.one_foro.domain.topico.dto.DatosRegistroTopico;
import com.aluracurso.one_foro.domain.topico.dto.datosActualizarTopico;

public interface ValidadordeRegistroTopico {
    void validar(DatosRegistroTopico datos);

    void  validarActualizar(datosActualizarTopico datos);
}
