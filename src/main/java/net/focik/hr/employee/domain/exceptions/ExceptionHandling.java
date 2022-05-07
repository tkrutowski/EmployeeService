package net.focik.hr.employee.domain.exceptions;

import lombok.extern.slf4j.Slf4j;
import net.focik.hr.team.domain.HttpResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@Slf4j
//@RestControllerAdvice
public class ExceptionHandling implements ErrorController {
    public final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a '%s' request.";
    public final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request.";
    public final String ERROR_PROCESSING_FILE = "Error occurred while processing file.";
    public final String NOT_ENOUGH_PERMISSION = "Nie masz wystarczających uprawnień.";
    public static final String ERROR_PATH = "/error";

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException() {
        return createHttpResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(ObjectAlreadyExistException.class)
    public ResponseEntity<HttpResponse> usernameExistException(ObjectAlreadyExistException exception) {
        return createHttpResponse(CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<HttpResponse> emailNotFoundException(ObjectNotFoundException exception) {
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
        log.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponse> notFoundException(NoResultException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponse> iOException(IOException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
    }

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<HttpResponse> notFound404(Exception exception) {
        int i=0;
        return createHttpResponse(NOT_FOUND, "There is no mapping for this URL");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HttpResponse> runtimeException(RuntimeException exception) {
        int i=0;
//        return createHttpResponse(UNAUTHORIZED, exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
    }



    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message){
        HttpResponse httpResponse = new HttpResponse(httpStatus.value(), httpStatus ,
                httpStatus.getReasonPhrase().toUpperCase(), message);

        return new ResponseEntity<>(httpResponse, httpStatus);
    }

    public String getErrorPath() {
        return ERROR_PATH;
    }
}
