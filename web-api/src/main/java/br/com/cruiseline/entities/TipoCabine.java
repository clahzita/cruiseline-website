/**
 * 
 */
package br.com.cruiseline.entities;

/**
 * @author clah
 *
 */
public enum TipoCabine {
  STUDIO(1000.00), 
  INSIDE(1300.00), 
  OCEANVIEW(1500.00), 
  BALCONY(1700.00);
  
  private Double valor;
  private TipoCabine(Double valor) {
    this.valor = valor;
  }
  
  public Double getValor() {
    return valor;
  }
  
}
