package forum.alura.com.dto.resposta;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarResposta(
        @NotNull
        Long id,
        String mensagem,
        Boolean solucao){

}
