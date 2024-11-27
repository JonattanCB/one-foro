package com.aluracurso.one_foro.controllers;

import com.aluracurso.one_foro.domain.topico.Status;
import com.aluracurso.one_foro.domain.topico.dto.DatosRegistroTopico;
import com.aluracurso.one_foro.domain.topico.dto.RespuestaRegistroTopico;
import com.aluracurso.one_foro.domain.topico.dto.datosActualizarTopico;
import com.aluracurso.one_foro.domain.topico.dto.datosTopico;
import com.aluracurso.one_foro.domain.topico.repository.TopicoRepository;
import com.aluracurso.one_foro.domain.topico.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<RespuestaRegistroTopico> RegistrarTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        var createTopico = topicoService.createTopico(datos);
        URI url = uriComponentsBuilder.path("autor/{id}").buildAndExpand(createTopico.id()).toUri();
        return ResponseEntity.created(url).body(createTopico);
    }

    @GetMapping
    public  ResponseEntity<Page<RespuestaRegistroTopico>> getAllTopicos( @PageableDefault(size = 10) Pageable pageable) {
        return  ResponseEntity.ok(topicoRepository.listaTopicoEstados(pageable, Status.ACTIVE).map(RespuestaRegistroTopico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaRegistroTopico>  ActualizarTopico(@RequestBody @Valid datosActualizarTopico datos) {
        var updateTopico = topicoService.updateTopico(datos);
        return ResponseEntity.ok(updateTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public  ResponseEntity EleminarTopico(@PathVariable Integer id){
        topicoService.deleteTopico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public  ResponseEntity<datosTopico> getTopicoById(@PathVariable Integer id){
        return  ResponseEntity.ok(topicoService.getTopicoById(id));
    }

}
