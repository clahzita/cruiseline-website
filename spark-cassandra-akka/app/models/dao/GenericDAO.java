/**
 * 
 */
package models.dao;

import java.util.List;

import models.exception.BDException;

/**
 * @author clah
 * @param <T>
 *
 */
public interface GenericDAO<T> {

  public void salvar(T objeto);
  
  public void alterar(T objeto, int id) throws BDException;
  
  public void remover(int id) throws BDException;
  
  public List<T> listarTodos();
  
  public T pegarPeloId(int id) throws BDException;
  
}
