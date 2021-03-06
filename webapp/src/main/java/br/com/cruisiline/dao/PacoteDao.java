/**
 * 
 */
package br.com.cruisiline.dao;

import java.util.ArrayList;
import java.util.List;
import br.com.cruisiline.entities.Pacote;
import br.com.cruisiline.exceptions.BDException;
import br.com.devmedia.entity.Nota;

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
    throw new BDException("Id n�o encontrado!");
    
  }

  @Override
  public void remover(int id) throws BDException {
    for (Pacote pacote : banco) {
      if(pacote.getId() == id) {
        banco.remove(pacote);
        System.out.println("Removido com sucesso!");
        return;
      }
    }
    throw new BDException("Id n�o encontrado!");
  }

  @Override
  public List<Pacote> listarTodos() {
    return banco;
  }

  @Override
  public Pacote pegarPeloId(int id) throws BDException {
    for (Pacote pacote : banco) {
      if(pacote.getId() == id) {
        System.err.println("Pacote encontrada!");
        return pacote;
      }
    }
    throw new BDException("Nenhum pacote com esse ID encontrada!");
   
  }
  
  private void carregarTodosPacotes() {
    //TODO carregar de um arquivo os pacotes existentes no sistema ou
    //chamar servi�o que crie os objetos para carregar.
  }
  

}
