package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pacote {
	
  private int id;
  private String lugar;
  private Integer precoMinimo;
  private Navio navio;
  private LocalDate partida;
  private Integer quantidadeDias;
  
  public Pacote() {
    
  }
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getNome() {
    return lugar;
  }
  public void setNome(String nome) {
    this.lugar = nome;
  }

  public int getPrecoMinimo() {
    return precoMinimo;
  }

  public void setPrecoMinimo(int i) {
    this.precoMinimo = i;
  }
  
  public Navio getNavio() {
    return navio;
  }
  public void setNavio(Navio navio) {
    this.navio = navio;
  }
  
  
  
}
