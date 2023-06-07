package forum.alura.com.dto.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAutenticarUsuario(
        @NotBlank @Email
        String email,
        @NotBlank
        String senha) {
}
