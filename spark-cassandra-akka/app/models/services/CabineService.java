package models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Cabine;
import models.Reserva;
import models.TipoCabine;
import models.dao.PacoteDao;
import models.exception.BDException;
import models.exception.BusinessException;

@Service
public class CabineService {

  @Autowired
  private PacoteDao repositorio;


  private List<Cabine> cabinesOceanView = new ArrayList<>();
  private List<Cabine> cabinesBalcony = new ArrayList<>();
  private List<Cabine> cabinesStudio = new ArrayList<>();
  private List<Cabine> cabinesInside = new ArrayList<>();

  public synchronized void organizarCabinesParallelStram(Integer idPacote) throws BDException {
    List<Cabine> todasCabines = repositorio.pegarPeloId(idPacote).getNavio().getTodasCabines();
    
    cabinesBalcony = todasCabines.parallelStream()
                      .filter(cabine -> cabine.getTipo() == TipoCabine.BALCONY && cabine.isDisponivel())
                      .collect(Collectors.toList());
    
    cabinesInside = todasCabines.parallelStream()
        .filter(cabine -> cabine.getTipo() == TipoCabine.INSIDE && cabine.isDisponivel())
        .collect(Collectors.toList());
    
    cabinesOceanView = todasCabines.parallelStream()
        .filter(cabine -> cabine.getTipo() == TipoCabine.OCEANVIEW && cabine.isDisponivel())
        .collect(Collectors.toList());
    
    cabinesStudio = todasCabines.parallelStream()
        .filter(cabine -> cabine.getTipo() == TipoCabine.STUDIO && cabine.isDisponivel())
        .collect(Collectors.toList());

  }
  
  public List<Cabine> pegarListaCabinesStudioDisponiveis() {
    
    if (cabinesStudio.size() != 25) {
      System.err.println("ERRO cabinesStudio:" + cabinesStudio.size() + "de 25");
    } else {
      System.out.println("Studio: " + cabinesStudio.size() + "de 25");
    }

    return this.cabinesStudio;
  }

  public List<Cabine> pegarListaCabinesBalconyDisponiveis() {
    
    if (cabinesBalcony.size() != 25) {
      System.err.println("ERRO: cabinesBalcony:" + cabinesBalcony.size() + "de 25");
    } else {
      System.out.println("Balcony: " + cabinesBalcony.size() + "de 25");
    }

    return this.cabinesBalcony;
  }

  public List<Cabine> pegarListaCabinesInsideDisponiveis() {
    
    if (cabinesInside.size() != 120) {
      System.err.println("ERRO: cabinesInside:" + cabinesInside.size() + " de 120");
    } else {
      System.out.println("Inside: " + cabinesInside.size() + " de 120");
    }

    return this.cabinesInside;
  }

  public List<Cabine> pegarListaCabinesOceanViewDisponiveis() {
    
    if (cabinesOceanView.size() != 140) {
      System.err.println("ERRO: cabinesOceanView:" + cabinesOceanView.size() + "de 140");
    } else {
      System.out.println("OceanView: " + cabinesOceanView.size() + "de 140");
    }

    return this.cabinesOceanView;
  }

  public synchronized int verificarDisponibilidadeCabinesSelecionadas(Reserva reserva,
      int idReserva) throws BDException, BusinessException {

    List<Cabine> todasCabines = reserva.getPacote().getNavio().getTodasCabines();

    int balcony = reserva.getCabineBalcony();
    int inside = reserva.getCabineInside();
    int oceanView = reserva.getCabineOceanView();
    int studio = reserva.getCabineStudio();

    int qtdeCabinesDisponiveisSelecionadas = 0;

    String exceptionMessage = null;
    for (Cabine cabine : todasCabines) {
      if (balcony == cabine.getId() || inside == cabine.getId() || oceanView == cabine.getId()
          || studio == cabine.getId()) {
        if (cabine.isDisponivel()) {
          cabine.setDisponivel(false);
          qtdeCabinesDisponiveisSelecionadas++;
        } else {
          exceptionMessage = cabine.getId() + " ";
        }
      }
    }

    if (exceptionMessage != null) {
      throw new BusinessException(
          "As cabines " + exceptionMessage + "não se encontram disponiveis");
    }

    repositorio.pegarPeloId(reserva.getPacote().getId()).getNavio().setTodasCabines(todasCabines);;

    return qtdeCabinesDisponiveisSelecionadas;

  }

  public synchronized int marcarCabineComoIndisponivel(Reserva reserva) throws BDException {

    List<Cabine> todasCabines = reserva.getPacote().getNavio().getTodasCabines();

    int balcony = reserva.getCabineBalcony();
    int inside = reserva.getCabineInside();
    int oceanView = reserva.getCabineOceanView();
    int studio = reserva.getCabineStudio();

    int qtdeCabinesDisponiveisSelecionadas = 0;

    for (Cabine cabine : todasCabines) {
      if (balcony == cabine.getId() || inside == cabine.getId() || oceanView == cabine.getId()
          || studio == cabine.getId()) {

        cabine.setDisponivel(false);
        qtdeCabinesDisponiveisSelecionadas++;

      }
    }

    repositorio.pegarPeloId(reserva.getPacote().getId()).getNavio().setTodasCabines(todasCabines);

    return qtdeCabinesDisponiveisSelecionadas;


  }

  public synchronized int marcarCabineComoDisponivel(Reserva reserva) throws BDException {

    List<Cabine> todasCabines = reserva.getPacote().getNavio().getTodasCabines();

    int balcony = reserva.getCabineBalcony();
    int inside = reserva.getCabineInside();
    int oceanView = reserva.getCabineOceanView();
    int studio = reserva.getCabineStudio();

    int qtdeCabinesDisponiveisSelecionadas = 0;

    for (Cabine cabine : todasCabines) {
      if (balcony == cabine.getId() || inside == cabine.getId() || oceanView == cabine.getId()
          || studio == cabine.getId()) {
        cabine.setDisponivel(true);
        qtdeCabinesDisponiveisSelecionadas++;

      }
    }

    repositorio.pegarPeloId(reserva.getPacote().getId()).getNavio().setTodasCabines(todasCabines);

    return qtdeCabinesDisponiveisSelecionadas;


  }




}
