package com.aluracurso.one_foro.domain.topico;

import com.aluracurso.one_foro.domain.autores.Autor;
import com.aluracurso.one_foro.domain.topico.dto.datosActualizarTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Table(name = "Topico")
@Entity(name = "topico")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    private String mensaje;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "autor_id")
    private Autor autor;

    private String curso;


    public Topico(String titulo, String mensaje, Autor autor, String curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.estado = Status.ACTIVE;
        this.fechaCreacion = LocalDateTime.now();
        this.autor = autor;
        this.curso = curso;
    }

    public void actualizarDatos(datosActualizarTopico datos) {

        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }

        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }

        if (datos.curso() != null) {
            this.curso = datos.curso();
        }

    }
    public void desactivarStatus() {
        this.estado = Status.INACTIVE;
    }

}
