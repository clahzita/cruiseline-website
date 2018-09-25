package br.com.cruisiline.entities;

public class Usuario {
  private String login;
  private int id;

  public Usuario(String login, int id) {
    super();
    this.login = login;
    this.id = id;
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
  public void setId(int id) {
    this.id = id;
  }
  
  
}
