package br.com.eullen.ecommerce.exception;

public class OperacaoInvalidaException extends RuntimeException {
    private static final long serialVersionUID = 2420443468419823583L;

    public OperacaoInvalidaException(String message) {
        super(message);
    }
}
