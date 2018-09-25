/**
 * 
 */
package br.com.cruisiline.dao;

import java.util.List;
import br.com.cruisiline.exceptions.BDException;

/**
 * @author clah
 * @param <T>
 *
 */
public interface GenericDAO<T> {

  public void salvar(T objeto);
  
  public void alterar(T objeto, int id) throws BDException;
  
  public int remover(T objeto);
  
  public List<T> listarTodos();
  
  public T pegarPeloId(int id);
  
}
