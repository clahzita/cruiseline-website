/**
 * 
 */
package models.data;

import java.util.List;

/**
 * @author clah
 * @param <T>
 *
 */
public interface GenericDAO<T> {

  public void salvar(T objeto);
  
  public void alterar(T objeto, int id);
  
  public void remover(int id);
  
  public List<T> listarTodos();
  
  public T pegarPeloId(int id);
  
}
