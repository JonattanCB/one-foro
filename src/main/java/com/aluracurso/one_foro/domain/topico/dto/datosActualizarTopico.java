package com.aluracurso.one_foro.domain.topico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record datosActualizarTopico(
        @NotNull(message = "Necesita un id para Actualizar") Integer id,
        @NotBlank(message = "Necesita un mensaje para Actualizar") String mensaje,
        @NotBlank(message = "Necesita un nombre de curso para Actualizar") String curso,
        @NotBlank(message = "Necesita un nombre de titulo para Actualizar") String titulo

) {
}
