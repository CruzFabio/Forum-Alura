package forum.alura.com.dto.usuario;

import forum.alura.com.domain.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotBlank
        String senha) {

        public DadosCadastroUsuario(DadosCadastroUsuario usuario, String senhaCriptografada){
                this(
                        usuario.nome,
                        usuario.email,
                        senhaCriptografada
                );
        }


}
