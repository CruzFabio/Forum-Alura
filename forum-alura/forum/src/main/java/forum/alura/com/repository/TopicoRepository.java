package forum.alura.com.repository;

import forum.alura.com.domain.Topico;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository <Topico, Long> {

    @Query("SELECT t FROM Topico t WHERE t.autor.usuarioId=:autor")
    Page<Topico> findAllByAutor(Long autor, Pageable paginacao);

    @Query("SELECT t FROM Topico t WHERE t.curso.cursoId=:curso")
    Page<Topico> findAllByCurso(Long curso, Pageable paginacao);
}
