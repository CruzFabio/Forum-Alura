package forum.alura.com.dto.usuario;

import forum.alura.com.domain.Usuario;

public record DadosListagemUsuario(Long id,String nome, String email) {
    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getUsuarioId(),usuario.getNome(), usuario.getEmail());
    }
}
