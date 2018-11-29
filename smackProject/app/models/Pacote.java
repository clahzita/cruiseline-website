package models;

import java.util.ArrayList;
import java.util.List;

public class Pacote {
	
  private int id;
  private String nome;
  private Integer precoMinimo;
  private List<Navio> navios;
  
  public Pacote() {
    this.navios = new ArrayList<>();
  }
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getPrecoMinimo() {
    return precoMinimo;
  }

  public void setPrecoMinimo(int i) {
    this.precoMinimo = i;
  }
  
  public List<Navio> getNavio() {
    return navios;
  }
  public void setNavio(List<Navio> navios) {
    this.navios = navios;
  }
  
  
  
}
