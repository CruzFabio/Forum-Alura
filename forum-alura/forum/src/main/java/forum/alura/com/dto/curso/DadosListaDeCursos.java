package forum.alura.com.dto.curso;

import forum.alura.com.domain.Curso;

public record DadosListaDeCursos(
        Long id,
        String nome,
        String categoria) {

    public DadosListaDeCursos(Curso curso) {
        this(curso.getCursoId(),curso.getNome(), curso.getCategoria());
    }
}
