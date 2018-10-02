package br.com.cruiseline.webapi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cruiseline.entities.Pacote;
import br.com.cruiseline.webapi.dao.PacoteDao;
import br.com.cruiseline.webapi.exceptions.BDException;
import br.com.cruiseline.webapi.exceptions.BusinessException;

@Service
public class PacoteService {
  
  @Autowired
  private PacoteDao repositorio;
  
  public List<Pacote> listarTodos() {
      return repositorio.listarTodos();
  }

  public Pacote find(Integer id) throws BDException {
    // TODO Auto-generated method stub
    return repositorio.pegarPeloId(id);
  }
  
  public void diminuirCapacidade(int idPacote) throws BDException, BusinessException {
    Pacote pacote = repositorio.pegarPeloId(idPacote);
    synchronized (this) {
      int capacidade = pacote.getCapacidade();
      capacidade--;
      if(capacidade < 0) {
        throw new BusinessException("Capacidade não pode ser menor que zero");
      }
      pacote.setCapacidade(capacidade);
    }
    repositorio.alterar(pacote, idPacote);
  }
  
  public void aumentarCapacidade(int idPacote) throws BusinessException, BDException {
    Pacote pacote = repositorio.pegarPeloId(idPacote);
    synchronized (this) {
      int capacidade = pacote.getCapacidade();
      capacidade++;
      if(capacidade > pacote.getMaximo()) {
        throw new BusinessException("Capacidade não pode ser maior que a quantidade máxima de pacotes");
      }
      pacote.setCapacidade(capacidade);
    }
    repositorio.alterar(pacote, idPacote);
  }

  public void alterar(Pacote pacote, int id) throws BDException {
    repositorio.alterar(pacote, id);

  }

  public Pacote pegarPeloId(int pacoteId) throws BDException {
    return repositorio.pegarPeloId(pacoteId);
  }
  
}
