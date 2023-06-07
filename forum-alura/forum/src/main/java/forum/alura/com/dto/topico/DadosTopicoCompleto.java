package forum.alura.com.dto.topico;


import forum.alura.com.domain.StatusTopico;
import forum.alura.com.domain.Topico;
import forum.alura.com.dto.curso.DadosCursoCompleto;
import forum.alura.com.dto.usuario.DadosUsuarioCompleto;

import java.time.LocalDateTime;

public record DadosTopicoCompleto(
        Long id,
        String titulo,
        String mensagem,

        LocalDateTime dataCriacao,
        StatusTopico status,
        DadosUsuarioCompleto autor,
        DadosCursoCompleto curso){

    public DadosTopicoCompleto(Topico topico){
        this(
                topico.getTopicoId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getDadosAutor(),
                topico.getDadosCurso());
    }
}

