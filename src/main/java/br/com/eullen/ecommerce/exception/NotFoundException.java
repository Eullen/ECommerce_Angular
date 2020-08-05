package br.com.eullen.ecommerce.exception;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5047310164752062722L;

    public NotFoundException(String message) {
        super(message);
    }
}
