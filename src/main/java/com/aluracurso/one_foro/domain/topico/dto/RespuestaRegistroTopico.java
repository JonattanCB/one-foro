package com.aluracurso.one_foro.domain.topico.dto;

import com.aluracurso.one_foro.domain.topico.Topico;

public record RespuestaRegistroTopico (
        Integer id,
        String titulo,
        String mensaje,
        String fechaCreacion
) {
    public RespuestaRegistroTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getFechaCreacion().toString());
    }
}
