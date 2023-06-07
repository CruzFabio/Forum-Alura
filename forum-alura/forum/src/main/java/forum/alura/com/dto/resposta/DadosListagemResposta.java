package forum.alura.com.dto.resposta;

import forum.alura.com.domain.Resposta;

import java.time.LocalDateTime;

public record DadosListagemResposta(
        Long id,
        String mensagem,
        LocalDateTime dataCriacao,
        Boolean solucao) {

    public DadosListagemResposta(Resposta resposta){
        this(
                resposta.getRespostaId(),
                resposta.getMensagem(),
                resposta.getDataCriacao(),
                resposta.getSolucao());
    }
}



