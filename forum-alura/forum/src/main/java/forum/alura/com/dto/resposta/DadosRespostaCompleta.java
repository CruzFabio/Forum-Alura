package forum.alura.com.dto.resposta;

import forum.alura.com.domain.Resposta;
import forum.alura.com.domain.Topico;
import forum.alura.com.domain.Usuario;

import java.time.LocalDateTime;

public record DadosRespostaCompleta(
        Long id,
        String mensagem,
        Topico topico,
        LocalDateTime dataCriacao,
        Usuario autor,
        Boolean solucao) {

    public DadosRespostaCompleta (Resposta resposta){
        this(
                resposta.getRespostaId(),
                resposta.getMensagem(),
                resposta.getTopico(),
                resposta.getDataCriacao(),
                resposta.getAutor(),
                resposta.getSolucao());
    }
}
