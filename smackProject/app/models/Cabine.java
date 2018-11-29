/**
 * 
 */
package models;

/**
 * @author clah
 *
 */
public class Cabine implements Comparable<Cabine>{
  private Integer id;
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
    this.id = numeroIdentificador;
    this.tipo = tipo;
    this.local = local;
    this.capacidade = capacidade;
    this.disponivel = true;
    this.preco = preco;
  }

  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
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

  @Override
  public int compareTo(Cabine cabine) {
    return this.getId().compareTo(cabine.getId());
  }
  
}
