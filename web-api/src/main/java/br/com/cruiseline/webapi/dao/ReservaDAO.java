package br.com.cruiseline.webapi.dao;

import java.util.List;
import br.com.cruiseline.entities.Reserva;
import br.com.cruisiline.exceptions.BDException;

public class ReservaDAO implements GenericDAO<Reserva> {

  @Override
  public void salvar(Reserva objeto) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void alterar(Reserva objeto, int id) throws BDException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void remover(int id) throws BDException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<Reserva> listarTodos() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Reserva pegarPeloId(int id) throws BDException {
    // TODO Auto-generated method stub
    return null;
  }

}
