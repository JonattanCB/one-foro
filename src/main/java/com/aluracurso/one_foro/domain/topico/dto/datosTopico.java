package com.aluracurso.one_foro.domain.topico.dto;

import com.aluracurso.one_foro.domain.topico.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record datosTopico(

        @NotNull(message = "Necesita un id para registrar") Integer id,
        @NotNull(message = "Necesita un autor para registrar") Integer idAutor,
        @NotBlank(message = "Necesita un mensaje para registrar") String mensaje,
        @NotBlank(message = "Necesita el nombre de un curso") String curso,
        @NotBlank(message = "Necesita el nombre del curso") String titulo
) {
    public datosTopico(Topico topico) {
        this(topico.getId(),topico.getAutor().getId(),topico.getMensaje(),topico.getCurso(),topico.getTitulo());
    }
}
