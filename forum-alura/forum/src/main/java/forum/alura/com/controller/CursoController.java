package forum.alura.com.controller;

import forum.alura.com.dto.curso.*;
import forum.alura.com.domain.Curso;
import forum.alura.com.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCursoCompleto> cadastrar (
            @RequestBody @Valid DadosCadastroCurso dados, UriComponentsBuilder uriComponentsBuilder) {
        var curso = new Curso(dados);
        repository.save(curso);

        var uri = uriComponentsBuilder.path("/cursos{id}").buildAndExpand(curso.getCursoId()).toUri();
        return ResponseEntity.created(uri).body(new DadosCursoCompleto(curso));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListaDeCursos>> listar(@PageableDefault(size = 10, sort ="cursoId")Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListaDeCursos::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarCurso dados){
        var curso = repository.getReferenceById(dados.id());
        curso.atualizarDados(dados);

        return ResponseEntity.ok(new DadosListaDeCursos(curso));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
    }

}
