package br.com.cruiseline.webapi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cruiseline.entities.Reserva;
import br.com.cruiseline.exceptions.BDException;
import br.com.cruiseline.webapi.dao.ReservaDAO;

@Service
public class ReservaService {
  
  @Autowired
  private ReservaDAO repositorio;
  
  
  public List<Reserva> buscarTodos() {
      return repositorio.listarTodos();
  }
  
  public Reserva encontrarUm(int id) {
      try {
        return repositorio.pegarPeloId(id);
      } catch (BDException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return null;
  }
  
  public void salvar(Reserva reserva) {
      repositorio.salvar(reserva);
      //TODO diminuir capacidade do pacote.
  }
  
  public void deletar(int id) {
      try {
        repositorio.remover(id);
      } catch (BDException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
  }
}
