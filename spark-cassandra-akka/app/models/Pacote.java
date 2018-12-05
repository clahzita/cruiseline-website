package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pacote {
	
  private int id;
  public String lugar;
  public Integer precoMinimo;
  public Navio navio;
  private LocalDate partida;
  private Integer quantidadeDias;
  
  public Pacote() {
    
  }
  
  
  public Pacote(int id, String lugar, Integer precoMinimo, Navio navio, LocalDate partida, Integer quantidadeDias) {
	super();
	this.id = id;
	this.lugar = lugar;
	this.precoMinimo = precoMinimo;
	this.navio = navio;
	this.partida = partida;
	this.quantidadeDias = quantidadeDias;
}


public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getLugar() {
    return lugar;
  }
  public void setLugar(String nome) {
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
