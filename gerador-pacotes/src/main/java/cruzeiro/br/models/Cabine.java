/**
 * 
 */
package cruzeiro.br.models;

/**
 * @author clah
 *
 */
public class Cabine implements Comparable<Cabine>{
  
  private Integer numero;
  private TipoCabine tipo;
  private boolean disponivel;
  private Double preco;
  
 

  public Cabine(Integer numero, TipoCabine tipo, boolean disponivel, Double preco) {
	super();
	this.numero = numero;
	this.tipo = tipo;
	this.disponivel = disponivel;
	this.preco = preco;
}

  public Integer getNumero() {
	return numero;
}

public void setNumero(Integer numero) {
	this.numero = numero;
}

public TipoCabine getTipo() {
	return tipo;
}

public void setTipo(TipoCabine tipo) {
	this.tipo = tipo;
}

public boolean isDisponivel() {
	return disponivel;
}

public void setDisponivel(boolean disponivel) {
	this.disponivel = disponivel;
}

public Double getPreco() {
	return preco;
}

public void setPreco(Double preco) {
	this.preco = preco;
}

@Override
  public int compareTo(Cabine cabine) {
    return this.getNumero().compareTo(cabine.getNumero());
  }
  
}
