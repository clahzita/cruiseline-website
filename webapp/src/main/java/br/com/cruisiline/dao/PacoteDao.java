/**
 * 
 */
package br.com.cruisiline.dao;

import java.util.ArrayList;
import java.util.List;
import br.com.cruisiline.entities.Pacote;
import br.com.cruisiline.exceptions.BDException;

/**
 * @author clah
 *
 */
public class PacoteDao implements GenericDAO<Pacote> {
  List<Pacote> banco = new ArrayList<>();
  
  @Override
  public void salvar(Pacote novo) {
    banco.add(novo);
    
  }

  @Override
  public void alterar(Pacote alterado, int id) throws BDException {
    for (Pacote pacote : banco) {
      if(pacote.getId() == id) {
        pacote.setCapacidade(alterado.getCapacidade());
        pacote.setNome(alterado.getNome());
        System.out.println("Editado com sucesso!");
        return;
      }
    }
    throw new BDException("Id não encontrado!");
    
  }

  @Override
  public int remover(Pacote objeto) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List<Pacote> listarTodos() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Pacote pegarPeloId(int id) {
    // TODO Auto-generated method stub
    return null;
  }

}
