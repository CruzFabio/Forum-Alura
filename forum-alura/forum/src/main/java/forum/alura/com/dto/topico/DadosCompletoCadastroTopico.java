package forum.alura.com.dto.topico;

import forum.alura.com.domain.Curso;
import forum.alura.com.domain.Usuario;

public record DadosCompletoCadastroTopico(
        String titulo,
        String mensagem,
        Usuario autor,
        Curso curso) {
}
