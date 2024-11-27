package com.aluracurso.one_foro.controllers;

import com.aluracurso.one_foro.domain.autores.Autor;
import com.aluracurso.one_foro.domain.autores.DatosAutenticarAutor;
import com.aluracurso.one_foro.infra.segurity.DatosJWTToken;
import com.aluracurso.one_foro.infra.segurity.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity autentificarAutor( @RequestBody @Valid DatosAutenticarAutor datosAutenticarAutor){
        Authentication auttoken = new UsernamePasswordAuthenticationToken(datosAutenticarAutor.login() , datosAutenticarAutor.clave());
        var autenticadoAutor = authenticationManager.authenticate(auttoken);
        var jwttoken = tokenService.generateToken((Autor) autenticadoAutor.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(jwttoken));
    }



}
