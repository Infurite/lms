package com.temchik.lms.controller;

import com.temchik.lms.common.exception.BusinessEntityNotFoundException;
import com.temchik.lms.common.exception.NamedException;
import com.temchik.lms.common.exception.NamedExceptionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {

    //private final EmailExceptionService emailExceptionService;

    @ExceptionHandler(NamedException.class)
    public ResponseEntity<NamedExceptionDTO> namedExceptionHandler(NamedException e) {
        return new ResponseEntity<>(new NamedExceptionDTO(e.getName(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessEntityNotFoundException.class)
    public ResponseEntity<NamedExceptionDTO> BusinessEntityNotFoundException(BusinessEntityNotFoundException e) {
        return new ResponseEntity<>(new NamedExceptionDTO(e.getName(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<NamedExceptionDTO> servletExceptionHandler(ServletException e) {
        return getResponseEntity("request.exception", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<NamedExceptionDTO> accessDeniedExceptionHandler(AccessDeniedException e) {
        return new ResponseEntity<>(new NamedExceptionDTO("access.denied", e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<NamedExceptionDTO>> handleValidationException(MethodArgumentNotValidException ex) {
        List<NamedExceptionDTO> exceptions = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            exceptions.add(new NamedExceptionDTO(error.getObjectName() + "." + error.getField() + ".invalid", error.getDefaultMessage()));
        }
        return new ResponseEntity<>(exceptions, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<NamedExceptionDTO> handleInvalidFormatException(Exception ex) {
        //emailExceptionService.sendExceptionEmail(ex);
        log.error(ex.getMessage(), ex);
        return getResponseEntity("I'm teapot", "Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            NumberFormatException.class
    })
    public ResponseEntity<NamedExceptionDTO> handleHttpMessageNotReadableException(Exception ex, HttpServletRequest request) {
        //emailExceptionService.sendInvalidFormatExceptionEmail(ex, request);
        return getResponseEntity("json.parse.exception", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<NamedExceptionDTO> getResponseEntity(String code, String message, HttpStatus status) {
        return new ResponseEntity<>(new NamedExceptionDTO(code, message), status);
    }
}

