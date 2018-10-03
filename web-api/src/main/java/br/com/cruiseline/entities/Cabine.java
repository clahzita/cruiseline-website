/**
 * 
 */
package br.com.cruiseline.entities;

/**
 * @author clah
 *
 */
public class Cabine {
  private int numeroIdentificador;
  private TipoCabine tipo;
  private LocalDeck local;
  private int capacidade;
  private boolean disponivel;
  private double preco;
  
  public Cabine() {
    this.disponivel=true;
  
  }
  
  public Cabine(int numeroIdentificador, TipoCabine tipo, LocalDeck local, int capacidade,
      double preco) {
    super();
    this.numeroIdentificador = numeroIdentificador;
    this.tipo = tipo;
    this.local = local;
    this.capacidade = capacidade;
    this.disponivel = true;
    this.preco = preco;
  }

  public int getNumeroIdentificador() {
    return numeroIdentificador;
  }

  public void setNumeroIdentificador(int numeroIdentificador) {
    this.numeroIdentificador = numeroIdentificador;
  }

  public TipoCabine getTipo() {
    return tipo;
  }

  public void setTipo(TipoCabine tipo) {
    this.tipo = tipo;
  }

  public LocalDeck getLocal() {
    return local;
  }

  public void setLocal(LocalDeck local) {
    this.local = local;
  }

  public int getCapacidade() {
    return capacidade;
  }

  public void setCapacidade(int capacidade) {
    this.capacidade = capacidade;
  }

  public boolean isDisponivel() {
    return disponivel;
  }

  public void setDisponivel(boolean disponivel) {
    this.disponivel = disponivel;
  }

  public double getPreco() {
    return preco;
  }

  public void setPreco(double preco) {
    this.preco = preco;
  }
  
}
