package forum.alura.com.repository;

import forum.alura.com.domain.Resposta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RespostaRepository extends JpaRepository <Resposta, Long> {

    @Query("SELECT R FROM Resposta R WHERE R.topico.topicoId=:topico")
    Page<Resposta> buscarPorTopico(Long topico, Pageable paginacao);

    @Query("SELECT R FROM Resposta R WHERE R.autor.usuarioId=:autor")
    Page<Resposta> buscarPorAutor(Long autor, Pageable paginacao);
}
