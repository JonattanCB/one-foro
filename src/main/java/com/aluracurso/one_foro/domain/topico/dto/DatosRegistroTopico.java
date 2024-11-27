package com.aluracurso.one_foro.domain.topico.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotNull(message = "Necesita un autor para registrar") Integer idAutor,
        @NotBlank(message = "Necesita un mensaje para registrar") String mensaje,
        @NotBlank(message = "Necesita el nombre de un curso") String curso,
        @NotBlank(message = "Necesita el nombre del curso") String titulo
) {
}
