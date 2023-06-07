package forum.alura.com.dto.resposta;

import forum.alura.com.domain.Resposta;
import forum.alura.com.dto.topico.DadosTopico;
import forum.alura.com.dto.usuario.DadosListagemUsuario;

import java.time.LocalDateTime;

public record DadosResposta(
        String mensagem,
        LocalDateTime dataCriacao,
        Boolean solucao,
        DadosListagemUsuario autor,
        DadosTopico topico) {
    public DadosResposta(Resposta resposta) {
        this(
                resposta.getMensagem(),
                resposta.getDataCriacao(),
                resposta.getSolucao(),
                new DadosListagemUsuario(resposta.getAutor()),
                new DadosTopico(resposta.getTopico()));
    }
}
