package com.aluracurso.one_foro.infra.segurity;

import com.aluracurso.one_foro.domain.autores.AutorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private  TokenService tokenService;

    @Autowired
    private AutorRepository autorRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var autHeader = request.getHeader("Authorization");
        if ( autHeader != null ) {
            var token =  autHeader.replace("Bearer ", "");
            var subject =  tokenService.getSubject(token);
            if (subject != null) {
                //token valido
                var autor = autorRepository.findByEmail(subject);
                var autenticacion = new UsernamePasswordAuthenticationToken(autor, null,autor.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autenticacion);
            }
        }
        filterChain.doFilter(request, response);
    }
}
