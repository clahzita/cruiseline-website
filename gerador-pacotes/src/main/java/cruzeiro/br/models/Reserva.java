/**
 * 
 */
package cruzeiro.br.models;

import java.util.List;

/**
 * @author clah
 *
 */
public class Reserva {
  
  private Integer id;
  
  private String usuario;
  
  private Pacote pacote;
  
  private List<Cabine> cabinesSelecionadas;    
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
  public Pacote getPacote() {
    return pacote;
  }
  
  public void setPacote(Pacote pacote) {
    this.pacote = pacote;
  }

public String getUsuario() {
	return usuario;
}

public void setUsuario(String usuario) {
	this.usuario = usuario;
}

public List<Cabine> getCabinesSelecionadas() {
	return cabinesSelecionadas;
}

public void setCabinesSelecionadas(List<Cabine> cabinesSelecionadas) {
	this.cabinesSelecionadas = cabinesSelecionadas;
}
   
}
