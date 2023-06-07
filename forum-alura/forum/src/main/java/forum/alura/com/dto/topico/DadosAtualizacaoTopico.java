package forum.alura.com.dto.topico;

import forum.alura.com.domain.StatusTopico;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
        @NotNull
        Long id,
        String titulo,
        String mensagem,
        StatusTopico status) {

}
