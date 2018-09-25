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
  private double preco;
  private Localizacao local;
  private int capacidade;
  
  public Cabine() {}

  public Cabine(int numeroIdentificador, TipoCabine tipo, double preco, Localizacao local,
      int capacidade) {
    super();
    this.numeroIdentificador = numeroIdentificador;
    this.tipo = tipo;
    this.preco = preco;
    this.local = local;
    this.capacidade = capacidade;
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

  public double getPreco() {
    return preco;
  }

  public void setPreco(double preco) {
    this.preco = preco;
  }

  public Localizacao getLocal() {
    return local;
  }

  public void setLocal(Localizacao local) {
    this.local = local;
  }

  public int getCapacidade() {
    return capacidade;
  }

  public void setCapacidade(int capacidade) {
    this.capacidade = capacidade;
  }  
  
}
