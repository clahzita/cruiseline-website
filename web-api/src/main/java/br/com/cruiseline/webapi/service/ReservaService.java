package br.com.cruiseline.webapi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cruiseline.entities.Pacote;
import br.com.cruiseline.entities.Reserva;
import br.com.cruiseline.webapi.dao.PacoteDao;
import br.com.cruiseline.webapi.dao.ReservaDAO;
import br.com.cruiseline.webapi.exceptions.BDException;
import br.com.cruiseline.webapi.exceptions.BusinessException;

@Service
public class ReservaService {

  @Autowired
  private ReservaDAO repositorioReserva;

  private PacoteService pacoteService;


  public List<Reserva> buscarTodos() {
    return repositorioReserva.listarTodos();
  }

  public Reserva encontrarUm(int id) throws BDException {

    return repositorioReserva.pegarPeloId(id);

  }

  public void salvar(Reserva reserva) throws BDException, BusinessException {    
    repositorioReserva.salvar(reserva);
  }

  public void editar(Reserva reserva, int id) throws BDException {
    repositorioReserva.alterar(reserva, id);

  }

  public void deletar(int idReserva) throws BusinessException, BDException {
    repositorioReserva.remover(idReserva);
    
  }
}
