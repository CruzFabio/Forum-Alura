package forum.alura.com.controller;

import forum.alura.com.dto.topico.*;
import forum.alura.com.domain.Topico;
import forum.alura.com.repository.CursoRepository;
import forum.alura.com.repository.TopicoRepository;
import forum.alura.com.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository repository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosTopicoCompleto> cadastrar(
            @RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriComponentsBuilder) {

       var usuario = usuarioRepository.findById(dados.autor()).orElseThrow(() -> new EntityNotFoundException("Autor não encontrado"));

       var curso = cursoRepository.findById(dados.curso()).orElseThrow(()-> new EntityNotFoundException("Curso não encontrado"));

        var dadosCadastroTopico = new DadosCompletoCadastroTopico(
                dados.titulo(), dados.mensagem(), usuario, curso);

        var topico = repository.save(new Topico(dadosCadastroTopico));
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getTopicoId()).toUri();
        return ResponseEntity.created(uri).body(new DadosTopicoCompleto(topico));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topico = repository.getReferenceById(dados.id());
        topico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosListagemTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity <DadosTopicoCompleto>listarTopicoPorId(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosTopicoCompleto(topico));
    }

}
