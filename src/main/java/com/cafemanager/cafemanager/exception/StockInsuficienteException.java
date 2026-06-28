package com.cafemanager.cafemanager.exception;

public class StockInsuficienteException
        extends RuntimeException {


  public StockInsuficienteException(String mensaje) {
    super(mensaje);
  }

}
