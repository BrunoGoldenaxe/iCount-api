package br.com.itech.icount.Infra.Exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {

    private HttpStatus code;

    private String message;

    public BadRequestException(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    public HttpStatus getCode() { return  code; }

    @Override
    public String getMessage() {
        return message;
    }
}
