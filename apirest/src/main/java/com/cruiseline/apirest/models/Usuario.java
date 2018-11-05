package com.cruiseline.apirest.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Usuario {
  private static AtomicInteger sequence = new AtomicInteger(0);
  private String login;
  private int id;

  public Usuario() {
    this.id = sequence.getAndIncrement();
  }
  
  public Usuario(String login) {
    this.login = login;
    this.id = sequence.getAndIncrement();
  }
  public String getLogin() {
    return login;
  }
  public void setLogin(String login) {
    this.login = login;
  }
  public int getId() {
    return id;
  }
  
  
}
