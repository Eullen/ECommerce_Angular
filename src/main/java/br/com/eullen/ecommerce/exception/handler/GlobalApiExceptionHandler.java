package br.com.eullen.ecommerce.exception.handler;

import br.com.eullen.ecommerce.exception.NotFoundException;
import br.com.eullen.ecommerce.exception.OperacaoInvalidaException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice(basePackages = {"br.com.eullen.ecommerce.controller"})
public class GlobalApiExceptionHandler {
    private static final long serialVersionUID = 1934739963205023581L;
    static final Logger LOG = LoggerFactory.getLogger(GlobalApiExceptionHandler.class);

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handle(ConstraintViolationException ex) {
        LOG.error("Valores inválidos", ex);
        List<ApiErro> erros = new ArrayList<>();

        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            ApiErro erro = new ApiErro();
            erro.setCodigo(violation.getMessageTemplate());
            erro.setMensagem(violation.getMessage());
            erros.add(erro);
        }

        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    @SuppressWarnings("rawtypes")
    public ResponseEntity<ApiErro> handle(Exception ex) {
        LOG.error("Erro Interno", ex);
        ApiErro error = new ApiErro();
        error.setMensagem(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiErro> handle(NotFoundException ex) {
        LOG.error("Item não encontrado", ex);
        ApiErro erro = new ApiErro();
        erro.setMensagem(ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = OperacaoInvalidaException.class)
    public ResponseEntity<ApiErro> handle(OperacaoInvalidaException ex) {
        LOG.error("Operação Não Suportada", ex);
        ApiErro erro = new ApiErro();
        erro.setMensagem(ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    static class ApiErro {

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String codigo;

        private String mensagem;

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    }
}