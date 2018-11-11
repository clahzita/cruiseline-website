package br.com.cruiseline.webapi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cruiseline.webapi.dao.ReservaDAO;
import br.com.cruiseline.webapi.entities.Reserva;
import br.com.cruiseline.webapi.entities.TipoCabine;
import br.com.cruiseline.webapi.exceptions.BDException;
import br.com.cruiseline.webapi.exceptions.BusinessException;

@Service
public class ReservaService {


  private ReservaDAO repositorioReserva = ReservaDAO.getInstance();

  @Autowired
  private CabineService cabineService;

  @Autowired
  private PacoteService pacoteService;

  public List<Reserva> buscarTodos() {
    return repositorioReserva.listarTodos();
  }

  public Reserva pegarPeloId(int id) throws BDException {

    return repositorioReserva.pegarPeloId(id);

  }

  public void salvar(Reserva reserva) throws BDException, BusinessException {
    if(reserva == null) {
      throw new BusinessException("Reserva null não pode ser salva no repositorio.");
    }
    repositorioReserva.salvar(reserva);
  }

  public void alterar(Reserva reserva, int idReserva) throws BDException, BusinessException{

    int todasCabinesSelecionadas = cabineService.verificarDisponibilidadeCabinesSelecionadas(reserva, idReserva);
    pacoteService.diminuirCapacidade(reserva.getPacote().getId(),todasCabinesSelecionadas);
    contabilizarPrecoTotal(reserva);
    try {
      repositorioReserva.alterar(reserva, idReserva);  
    } catch (BDException e) {
      cabineService.marcarCabineComoDisponivel(reserva);
      pacoteService.aumentarCapacidade(reserva.getPacote().getId(), todasCabinesSelecionadas);
    }
        

  }

  private void contabilizarPrecoTotal(Reserva reserva) {

    if(reserva.getCabineBalcony()!=0) {
      reserva.setCustoTotal(reserva.getCustoTotal()+TipoCabine.BALCONY.getValor());
    }

    if(reserva.getCabineInside()!=0) {
      reserva.setCustoTotal(reserva.getCustoTotal()+TipoCabine.INSIDE.getValor());
    }

    if(reserva.getCabineOceanView()!=0) {
      reserva.setCustoTotal(reserva.getCustoTotal()+TipoCabine.OCEANVIEW.getValor());
    }

    if(reserva.getCabineStudio()!=0) {
      reserva.setCustoTotal(reserva.getCustoTotal()+TipoCabine.STUDIO.getValor());
    }
  }

  public void deletar(int idReserva) throws BusinessException, BDException {
    Reserva reserva = repositorioReserva.pegarPeloId(idReserva);
    
    int todasCabinesExcluidas = cabineService.marcarCabineComoIndisponivel(reserva);
    pacoteService.aumentarCapacidade(reserva.getPacote().getId(),todasCabinesExcluidas);
    
    try {
      repositorioReserva.remover(idReserva);  
    } catch (BDException e) {
      cabineService.marcarCabineComoDisponivel(reserva);
      pacoteService.diminuirCapacidade(reserva.getPacote().getId(), todasCabinesExcluidas);
    }
    

  }

  public List<Reserva> buscarTodasReservasPorPacote(int id) throws BusinessException {
    return repositorioReserva.listarTodosPorPacote(id);
  }
}
