package forum.alura.com.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(value = {EntityNotFoundException.class, MissingPathVariableException.class})
    public ResponseEntity tratarErro404(){
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(message404());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors().stream().map(erroDeValidacao::new).toList();
        return ResponseEntity.badRequest().body(erros);
    }

    private record erroDeValidacao(String campo, String error) {
        public erroDeValidacao(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    private Map<String, String> message404() {
        Map<String, String> data = new HashMap<>();
        data.put("message", "record not found");
        return data;
    }

}
