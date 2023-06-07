package forum.alura.com.controller;

import forum.alura.com.dto.usuario.*;
import forum.alura.com.domain.Usuario;
import forum.alura.com.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosUsuarioCompleto> cadastrar(
            @RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder) {

       Usuario usuario = new Usuario(dados);
       usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
       repository.save(usuario);

        var uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getUsuarioId()).toUri();
        return ResponseEntity.created(uri).body(new DadosUsuarioCompleto(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listar(@PageableDefault(size = 10, sort = "usuarioId")Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemUsuario::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarUsuario dados){
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarDados(dados);

        return ResponseEntity.ok(new DadosListagemUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
