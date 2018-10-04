/**
 * 
 */
package br.com.cruiseline.entities;

import java.util.List;

/**
 * @author clah
 *
 */
public class Reserva {
  
  private int id;
  
  private Usuario usuario;
  
  private Pacote pacote;
  
  private List<Cabine> cabinesSelecionadas;  
  
  private Integer cabineStudio;
  private Integer cabineBalcony;
  private Integer cabineOceanView;
  private Integer cabineInside;
  
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
  
  public Integer getCabineStudio() {
    return cabineStudio;
  }

  public void setCabineStudio(Integer cabineStudio) {
    this.cabineStudio = cabineStudio;
  }

  public Integer getCabineBalcony() {
    return cabineBalcony;
  }

  public void setCabineBalcony(Integer cabineBalcony) {
    this.cabineBalcony = cabineBalcony;
  }

  public Integer getCabineOceanView() {
    return cabineOceanView;
  }

  public void setCabineOceanView(Integer cabineOceanView) {
    this.cabineOceanView = cabineOceanView;
  }

  public Integer getCabineInside() {
    return cabineInside;
  }

  public void setCabineInside(Integer cabineInside) {
    this.cabineInside = cabineInside;
  }

  public int getNumeroPassageiros() {
    return numeroPassageiros;
  }
  
  public void setNumeroPassageiros(int numeroPassageiros) {
    this.numeroPassageiros = numeroPassageiros;
  }
  
  public double getCustoTotal() {
    //calcularCustoTotal();
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
