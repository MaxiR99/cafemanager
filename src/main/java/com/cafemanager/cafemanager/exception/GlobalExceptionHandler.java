package com.cafemanager.cafemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(RecursoNoEncontradoException.class)
  public ResponseEntity<ErrorResponse> manejarNoEncontrado(
          RecursoNoEncontradoException ex
  ) {

    ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage()
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(error);

  }

  @ExceptionHandler(ReglaNegocioException.class)
  public ResponseEntity<ErrorResponse> manejarReglaNegocio(
          ReglaNegocioException ex
  ) {

    ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage()
    );

    return ResponseEntity.badRequest()
            .body(error);

  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> manejarGeneral(
          Exception ex
  ) {

    ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error interno del servidor"
    );

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(error);

  }

}
