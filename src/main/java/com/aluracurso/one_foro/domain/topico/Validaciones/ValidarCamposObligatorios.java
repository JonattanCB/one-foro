package com.aluracurso.one_foro.domain.topico.Validaciones;

import com.aluracurso.one_foro.domain.topico.dto.DatosRegistroTopico;
import com.aluracurso.one_foro.domain.topico.dto.datosActualizarTopico;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidarCamposObligatorios implements ValidadordeRegistroTopico {


    @Override
    public void validar(DatosRegistroTopico datos) {
        if (datos.idAutor() == 0){
            throw new ValidationException("El campo idAutor es obligatorio");
        }

        if (datos.mensaje().isBlank()|| datos.mensaje().isEmpty()){
            throw new ValidationException("El campo mensaje es obligatorio");
        }

        if (datos.titulo().isBlank()|| datos.titulo().isEmpty()){
            throw new ValidationException("El campo titulo es obligatorio");
        }

        if (datos.curso().isBlank()|| datos.curso().isEmpty()){
            throw new ValidationException("El campo curso es obligatorio");
        }

    }

    @Override
    public void validarActualizar(datosActualizarTopico datos) {

        if (datos.id() == 0){
            throw new ValidationException("El campo idAutor es obligatorio");
        }

        if (datos.mensaje().isBlank()|| datos.mensaje().isEmpty()){
            throw new ValidationException("El campo mensaje es obligatorio");
        }

        if (datos.titulo().isBlank()|| datos.titulo().isEmpty()){
            throw new ValidationException("El campo titulo es obligatorio");
        }

        if (datos.curso().isBlank()|| datos.curso().isEmpty()){
            throw new ValidationException("El campo curso es obligatorio");
        }
    }
}
