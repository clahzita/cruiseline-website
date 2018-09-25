package br.com.cruiseline.entities;

public class Pacote {
  private int capacidade;
  private int id;
  private String nome;
  
  public Pacote() { }
  
  public Pacote(int capacidade, int id, String nome) {
    super();
    this.capacidade = capacidade;
    this.id = id;
    this.nome = nome;
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
  
  
}
