package br.com.eullen.ecommerce.exception;

public class AuthenticationException extends RuntimeException {
    private static final long serialVersionUID = -5047310164752062722L;

    public AuthenticationException(String message) {
        super(message);
    }
}
