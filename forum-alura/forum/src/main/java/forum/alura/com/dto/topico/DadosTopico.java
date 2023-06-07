package forum.alura.com.dto.topico;

import forum.alura.com.domain.StatusTopico;
import forum.alura.com.domain.Topico;

public record DadosTopico(String titulo, String mensagem, StatusTopico status) {
    public DadosTopico(Topico topico){
        this(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getStatus());
    }

}
