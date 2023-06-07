package forum.alura.com.dto.topico;

import forum.alura.com.domain.StatusTopico;
import forum.alura.com.domain.Topico;
import forum.alura.com.dto.curso.DadosListaDeCursos;
import forum.alura.com.dto.usuario.DadosListagemUsuario;

import java.time.LocalDateTime;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        DadosListagemUsuario autor,
        DadosListaDeCursos curso) {

    public DadosListagemTopico(Topico topico){
        this(
                topico.getTopicoId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                new DadosListagemUsuario(topico.getAutor()),
                new DadosListaDeCursos(topico.getCurso()));
    }
}
