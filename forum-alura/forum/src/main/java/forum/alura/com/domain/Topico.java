package forum.alura.com.domain;

import forum.alura.com.dto.curso.DadosCursoCompleto;
import forum.alura.com.dto.curso.DadosListaDeCursos;
import forum.alura.com.dto.topico.DadosAtualizacaoTopico;
import forum.alura.com.dto.topico.DadosCadastroTopico;
import forum.alura.com.dto.topico.DadosCompletoCadastroTopico;
import forum.alura.com.dto.usuario.DadosListagemUsuario;
import forum.alura.com.dto.usuario.DadosUsuarioCompleto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topico_id")
    private Long topicoId;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "mensagem")
    private String mensagem;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusTopico status = StatusTopico.NAO_RESPONDIDO;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private Set<Resposta> respostas = new HashSet<>();

    public Topico(Long topicoId) {
        this.topicoId = topicoId;
    }

//    public Topico(DadosCadastroTopico dados) {
//        this.titulo = dados.titulo();
//        this.mensagem = dados.mensagem();
//        this.autor = dados.autor();
//        this.curso = dados.curso();
//
//    }

    public Topico(DadosCompletoCadastroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.autor = dados.autor();
        this.curso = dados.curso();
    }

    public DadosUsuarioCompleto getDadosAutor() {
        return new DadosUsuarioCompleto(this.autor);
    }

    public DadosCursoCompleto getDadosCurso() {
        return new DadosCursoCompleto(this.curso);
    }

    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if(dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if(dados.mensagem() != null){
            this.mensagem = dados.mensagem();
        }
        if(dados.status() != null){
            this.status = dados.status();
        }
    }

}
