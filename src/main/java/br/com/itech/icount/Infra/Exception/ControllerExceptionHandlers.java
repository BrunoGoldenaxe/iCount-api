package br.com.itech.icount.Infra.Exception;


import br.com.itech.icount.Infra.MessageDTO;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ControllerAdvice
public class ControllerExceptionHandlers implements WebMvcConfigurer {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageDTO handleNotFoundException(NotFoundException e) {
        return new MessageDTO(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO handleBadRequestException(BadRequestException e) {
        return new MessageDTO(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public MessageDTO handleUnauthorizedException(UnauthorizedException e) {
        return new MessageDTO(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public MessageDTO handleBusinessException(BusinessException e) {
        return new MessageDTO(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
    }

    @ExceptionHandler(PropertyValueException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public MessageDTO handlePropertyValueException(PropertyValueException e) {
        return new MessageDTO(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
    }
}
