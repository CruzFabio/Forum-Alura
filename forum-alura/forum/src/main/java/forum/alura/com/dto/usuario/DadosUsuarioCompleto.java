package forum.alura.com.dto.usuario;

import forum.alura.com.domain.Usuario;

public record DadosUsuarioCompleto(Long id, String nome, String email) {

    public DadosUsuarioCompleto (Usuario usuario){
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail());
    }
}
