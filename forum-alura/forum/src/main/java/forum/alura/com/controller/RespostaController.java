package forum.alura.com.controller;

import forum.alura.com.dto.resposta.*;
import forum.alura.com.infra.exception.TratadorDeErros;
import forum.alura.com.repository.RespostaRepository;
import forum.alura.com.domain.Resposta;
import forum.alura.com.domain.Topico;
import forum.alura.com.repository.TopicoRepository;
import forum.alura.com.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/resposta")
public class RespostaController {
    @Autowired
    private RespostaRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosResposta> cadastrar(
            @RequestBody @Valid DadosCadastroReposta dados, UriComponentsBuilder uriComponentsBuilder){
        var autor = usuarioRepository.findById(dados.autor()).orElseThrow(()
                -> new EntityNotFoundException("Autor não encontrado"));

        var topico = topicoRepository.findById(dados.topico()).orElseThrow(()
                -> new EntityNotFoundException("Tópico não encontrado"));

        var dadosCompletoResposta = new DadosCompletoResposta(dados.mensagem(), autor, topico);

        Resposta resposta = repository.save(new Resposta(dadosCompletoResposta));

        var uri = uriComponentsBuilder.path("/resposta{id}").buildAndExpand(resposta.getRespostaId()).toUri();
        return ResponseEntity.created(uri).body(new DadosResposta(resposta));
    }
    @GetMapping
    public ResponseEntity<Page<DadosListagemResposta>> listar(@PageableDefault(size=10, sort="respostaId")Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemResposta::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemResposta> atualizar(@RequestBody @Valid DadosAtualizarResposta dados) {
        var resposta = repository.getReferenceById(dados.id());
        resposta.atualizarDados(dados);
        return ResponseEntity.ok(new DadosListagemResposta(resposta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
