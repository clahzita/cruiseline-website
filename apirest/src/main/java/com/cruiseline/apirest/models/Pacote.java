package com.cruiseline.apirest.models;

import java.util.ArrayList;
import java.util.List;

public class Pacote {
  private int maximo;
  private int capacidade;
  private int id;
  private String nome;
  private int precoMinimo;
  private List<Cabine> navio;
  
  public Pacote() {
    this.navio = new ArrayList<>();
  }
  public int getMaximo() {
    return maximo;
  }
  
  public void setMaximo(int maximo) {
    this.maximo = maximo;
  }
  
  public int getCapacidade() {
    return capacidade;
  }
  public void setCapacidade(int capacidade) {
    this.capacidade = capacidade;
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
  
  public List<Cabine> getNavio() {
    return navio;
  }
  public void setNavio(List<Cabine> navio) {
    this.navio = navio;
  }
  
  
  
}
