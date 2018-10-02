package br.com.cruiseline.webapi.exceptions;

public class BusinessException extends Exception {
  public BusinessException(String messagem) {
    super(messagem);
  }
}
