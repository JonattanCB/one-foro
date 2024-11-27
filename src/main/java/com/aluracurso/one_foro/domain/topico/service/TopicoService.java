package com.aluracurso.one_foro.domain.topico.service;

import com.aluracurso.one_foro.domain.autores.Autor;
import com.aluracurso.one_foro.domain.autores.AutorRepository;
import com.aluracurso.one_foro.domain.topico.Topico;
import com.aluracurso.one_foro.domain.topico.Validaciones.ValidadordeRegistroTopico;
import com.aluracurso.one_foro.domain.topico.dto.DatosRegistroTopico;
import com.aluracurso.one_foro.domain.topico.dto.RespuestaRegistroTopico;
import com.aluracurso.one_foro.domain.topico.dto.datosActualizarTopico;
import com.aluracurso.one_foro.domain.topico.dto.datosTopico;
import com.aluracurso.one_foro.domain.topico.repository.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private List<ValidadordeRegistroTopico> validadores;

    public RespuestaRegistroTopico createTopico(DatosRegistroTopico datos) {
        validadores.forEach(v -> v.validar(datos));

        Autor autor = autorRepository.getAutorById(datos.idAutor());

        if (autor == null) {
            throw new ValidationException("Autor no encontrado");
        }

        var topiconew = new Topico(datos.titulo(), datos.mensaje(), autor, datos.curso());

        topicoRepository.save(topiconew);

        return new RespuestaRegistroTopico(topiconew);
    }

    public RespuestaRegistroTopico updateTopico(datosActualizarTopico datos) {
        validadores.forEach(v -> v.validarActualizar(datos));

        var topico = findTopicoById(datos.id());

        topico.actualizarDatos(datos);

        return new RespuestaRegistroTopico(topico);
    }

    public void deleteTopico(int id) {
        Topico topico = findTopicoById(id);
        topico.desactivarStatus();
    }

    public datosTopico getTopicoById(int id) {
        Topico topico = findTopicoById(id);
        return new datosTopico(topico);
    }

    private Topico findTopicoById(int id) {
        Topico topico = topicoRepository.getTopicosById(id);

        if (topico == null) {
            throw new ValidationException("Topico no encontrado");
        }
        return topico;
    }

}
