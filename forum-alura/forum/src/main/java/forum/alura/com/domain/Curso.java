package forum.alura.com.domain;

import forum.alura.com.dto.curso.DadosAtualizarCurso;
import forum.alura.com.dto.curso.DadosCadastroCurso;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id")
    private Long cursoId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "categoria")
    private String categoria;

    public Curso(DadosCadastroCurso dadosCadastroCurso) {
        this.nome = dadosCadastroCurso.nome();
        this.categoria = dadosCadastroCurso.categoria();
    }

    public Curso(Long cursoId) {
        this.cursoId = cursoId;
    }

    public void atualizarDados(DadosAtualizarCurso dadosAtualizarCurso){
            if(dadosAtualizarCurso.nome() != null){
                this.nome = dadosAtualizarCurso.nome();
            }
            if(dadosAtualizarCurso.categoria() != null){
                this.categoria = dadosAtualizarCurso.categoria();
            }
      }
}
