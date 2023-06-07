package forum.alura.com.dto.topico;

import forum.alura.com.domain.StatusTopico;
import java.time.LocalDateTime;

public record DadosRespostaTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        Long autor,
        Long curso) {
}
