package br.com.itech.icount.Infra.Exception;

import org.hibernate.HibernateException;
import org.hibernate.internal.util.StringHelper;
import org.springframework.http.HttpStatus;

public class PropertyValueException extends HibernateException {
    private HttpStatus code;

    private String propertyName;

    public PropertyValueException(HttpStatus code, String message){
        super(message);
        this.code = code;
    }

    public HttpStatus getCode() { return code; }

    @Override
    public String getMessage() { return super.getMessage() + " : " + StringHelper.qualify("campo:",propertyName); }

}
