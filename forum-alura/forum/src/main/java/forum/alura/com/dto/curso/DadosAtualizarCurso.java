package forum.alura.com.dto.curso;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarCurso(
        @NotNull
        Long id,
        String nome,
        String categoria) {

}
