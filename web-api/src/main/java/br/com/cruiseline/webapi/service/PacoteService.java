package br.com.cruiseline.webapi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cruiseline.webapi.dao.PacoteDao;
import br.com.cruiseline.webapi.entities.Pacote;
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
    return repositorio.pegarPeloId(id);
  }

  public synchronized int diminuirCapacidade(int idPacote, int qtdeCabinesSelecionadas)
      throws BDException, BusinessException {

    Pacote pacote = repositorio.pegarPeloId(idPacote);
    int capacidade;

    capacidade = pacote.getCapacidade();
    int capacidadeAtualizada = capacidade - qtdeCabinesSelecionadas;
    if (capacidade < 0) {
      throw new BusinessException("Capacidade não pode ser menor que zero.");
    }
    pacote.setCapacidade(capacidadeAtualizada);
    repositorio.alterar(pacote, idPacote);
    
    return capacidade;
  }
  
  public synchronized int aumentarCapacidade(int idPacote, int qtdeCabinesExcluidas)
      throws BDException, BusinessException {

    Pacote pacote = repositorio.pegarPeloId(idPacote);
    int capacidade;

    capacidade = pacote.getCapacidade();
    int capacidadeAtualizada = capacidade + qtdeCabinesExcluidas;
    if (capacidade > pacote.getMaximo()) {
      throw new BusinessException("Capacidade não pode ser maior que o máximo.");
    }
    pacote.setCapacidade(capacidadeAtualizada);
    repositorio.alterar(pacote, idPacote);
    
    return capacidade;
  }

  public void alterar(Pacote pacote, int id) throws BDException {
    repositorio.alterar(pacote, id);

  }

  public Pacote pegarPeloId(int pacoteId) throws BDException {
    return repositorio.pegarPeloId(pacoteId);
  }


}
