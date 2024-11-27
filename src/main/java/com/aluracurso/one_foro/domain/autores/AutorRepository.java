package com.aluracurso.one_foro.domain.autores;

import org.springframework.data.jpa.repository.JpaRepository;
public interface AutorRepository extends JpaRepository<Autor, Integer> {
    Autor findByEmail(String email);
    Autor getAutorById(Integer id);
}
