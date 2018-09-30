/**
 * 
 */
package br.com.cruiseline.entities;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * @author clah
 *
 */
public class Reserva {
  
  private int id;
  
  private Usuario usuario;
  
  private Pacote pacote;
  
  private List<Cabine> cabinesSelecionadas;  
  
  private int numeroPassageiros;
  
  private double custoTotal;
  

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public Usuario getUsuario() {
    return usuario;
  }
  
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  public Pacote getPacote() {
    return pacote;
  }
  
  public void setPacote(Pacote pacote) {
    this.pacote = pacote;
  }
  
  public List<Cabine> getCabinesSelecionadas() {
    return cabinesSelecionadas;
  }
  
  public void setCabinesSelecionadas(List<Cabine> cabinesSelecionadas) {
    this.cabinesSelecionadas = cabinesSelecionadas;
  }
  
  public int getNumeroPassageiros() {
    return numeroPassageiros;
  }
  
  public void setNumeroPassageiros(int numeroPassageiros) {
    this.numeroPassageiros = numeroPassageiros;
  }
  
  public double getCustoTotal() {
    calcularCustoTotal();
    return custoTotal;
  }
  
  public void setCustoTotal(double custoTotal) {
      this.custoTotal = custoTotal;
  }
  
  public void calcularCustoTotal() {
    for (Cabine cabine : cabinesSelecionadas) {
      custoTotal+= cabine.getPreco();
    }
  }



  
}
