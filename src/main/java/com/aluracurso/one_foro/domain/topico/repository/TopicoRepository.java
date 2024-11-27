package com.aluracurso.one_foro.domain.topico.repository;

import com.aluracurso.one_foro.domain.topico.Status;
import com.aluracurso.one_foro.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico,Integer> {

    @Query("select t from  topico t where  t.estado= :estado")
    Page<Topico> listaTopicoEstados (Pageable paginacion , Status estado);
    boolean existsByTitulo(String titulo);

    boolean existsByMensaje(String mensaje);

    Topico getTopicosById(Integer id);

}
