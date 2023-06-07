package forum.alura.com.dto.resposta;

import forum.alura.com.domain.Topico;
import forum.alura.com.domain.Usuario;

public record DadosCompletoResposta(String mensagem, Usuario autor, Topico topico) {
}
