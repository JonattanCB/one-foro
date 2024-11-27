package com.aluracurso.one_foro.infra.errores;

public class ValidadorException extends Throwable {
    public ValidadorException(String mensaje) {
        super(mensaje);
    }
}
