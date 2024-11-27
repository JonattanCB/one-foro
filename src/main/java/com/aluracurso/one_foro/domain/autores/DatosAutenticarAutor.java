package com.aluracurso.one_foro.domain.autores;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticarAutor(
        @NotBlank String login,
        @NotBlank String clave
) {
}
