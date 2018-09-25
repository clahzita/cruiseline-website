/**
 * 
 */
package br.com.cruiseline.webapi.dao;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import br.com.cruiseline.entities.Pacote;
import br.com.cruiseline.exceptions.BDException;

/**
 * @author clah
 *
 */
@Component
public class PacoteDao implements GenericDAO<Pacote> {
  List<Pacote> banco = new ArrayList<>();
  
  @PostConstruct
  public void iniciar() {
    
  }
  
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
  

}
