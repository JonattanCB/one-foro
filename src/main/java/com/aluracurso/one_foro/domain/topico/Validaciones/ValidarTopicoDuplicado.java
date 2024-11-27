package com.aluracurso.one_foro.domain.topico.Validaciones;

import com.aluracurso.one_foro.domain.topico.Topico;
import com.aluracurso.one_foro.domain.topico.dto.DatosRegistroTopico;
import com.aluracurso.one_foro.domain.topico.dto.datosActualizarTopico;
import com.aluracurso.one_foro.domain.topico.repository.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidarTopicoDuplicado implements ValidadordeRegistroTopico{

    @Autowired
    private TopicoRepository topicoRepository;
    @Override
    public void validar(DatosRegistroTopico datosRegistro) {
        List<Topico> listaTopicos = topicoRepository.findAll();
        if (!(listaTopicos.isEmpty())) {
            if (topicoRepository.existsByTitulo(datosRegistro.titulo())){
                throw new ValidationException("Titulo duplicado");
            }
            if (topicoRepository.existsByMensaje(datosRegistro.mensaje())){
                throw new ValidationException("Mensaje duplicado");
            }
        }
    }

    @Override
    public void validarActualizar(datosActualizarTopico datos) {
        List<Topico> listaTopicos = topicoRepository.findAll();
        if (!(listaTopicos.isEmpty())) {
            if (topicoRepository.existsByTitulo(datos.titulo())){
                throw new ValidationException("Titulo duplicado");
            }
            if (topicoRepository.existsByMensaje(datos.mensaje())){
                throw new ValidationException("Mensaje duplicado");
            }
        }
    }

}
