package forum.alura.com.dto.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(

        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        Long autor,
        @NotNull
        Long curso){
}
