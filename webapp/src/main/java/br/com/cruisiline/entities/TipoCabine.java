/**
 * 
 */
package br.com.cruisiline.entities;

/**
 * @author clah
 *
 */
public enum TipoCabine {
  STUDIO, INSIDE, OCEANVIEW(3), BALCONY(4), SPA(5);
  
  private final int valor;
  OpcoesMenu(int valorOpcao){
      valor = valorOpcao;
  }
  public int getValor(){
      return valor;
  }

}
