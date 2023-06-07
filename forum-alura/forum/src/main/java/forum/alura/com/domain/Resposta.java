package forum.alura.com.domain;

import forum.alura.com.dto.resposta.DadosAtualizarResposta;
import forum.alura.com.dto.resposta.DadosCompletoResposta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Resposta")
@Table(name = "respostas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resposta_id")
    private Long respostaId;

    @Column(name = "mensagem")
    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "topico_id")
    private Topico topico;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "autor_id")
    private Usuario autor;

    @Column(name = "solucao")
    private Boolean solucao = false;

    public Resposta(DadosCompletoResposta dados){
        this.mensagem = dados.mensagem();
        this.topico = dados.topico();
        this.autor = dados.autor();
    }

    public void atualizarDados(DadosAtualizarResposta dados) {
        if(dados.mensagem() != null){
            this.mensagem = dados.mensagem();
        }
        if(dados.solucao() != null){
            this.solucao = dados.solucao();
        }
    }

}
