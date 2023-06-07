package forum.alura.com.dto.curso;

import forum.alura.com.domain.Curso;

public record DadosCursoCompleto(
        Long id,
        String nome,
        String categoria) {

    public DadosCursoCompleto (Curso curso){
        this(
                curso.getCursoId(),
                curso.getNome(),
                curso.getCategoria());
    }
}
