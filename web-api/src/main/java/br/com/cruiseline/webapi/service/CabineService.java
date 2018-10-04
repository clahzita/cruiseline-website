package br.com.cruiseline.webapi.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cruiseline.entities.Cabine;
import br.com.cruiseline.entities.Reserva;
import br.com.cruiseline.webapi.concurrent.OrganizadorCabines;
import br.com.cruiseline.webapi.dao.PacoteDao;
import br.com.cruiseline.webapi.exceptions.BDException;
import br.com.cruiseline.webapi.exceptions.BusinessException;

@Service
public class CabineService {

  @Autowired
  private PacoteDao repositorio;


  private List<Cabine> cabinesOceanView = new ArrayList<>();
  private List<Cabine> cabinesBalcony = new ArrayList<>();
  private List<Cabine> cabinesStudio = new ArrayList<>();
  private List<Cabine> cabinesInside = new ArrayList<>();


  public void organizarCabinesPorTipo(int idPacote) throws BDException, BusinessException {

    cabinesOceanView = new ArrayList<>();
    cabinesBalcony = new ArrayList<>();
    cabinesStudio = new ArrayList<>();
    cabinesInside = new ArrayList<>();

    int numberOfThreads = 20;
    final List<Thread> threads = new ArrayList<>(numberOfThreads);

    List<Cabine> todasCabines = repositorio.pegarPeloId(idPacote).getNavio();

    if (todasCabines.isEmpty() || todasCabines == null) {
      throw new BusinessException("o navio não foi instanciado no pacote com id " + idPacote);
    }

    // definição de quantas cabines cada thread vai analisar
    int quantidadeCabinesParaAnalise = todasCabines.size() / numberOfThreads;


    for (int i = 0; i < numberOfThreads; i++) {

      int inicioBusca = i * quantidadeCabinesParaAnalise;
      int fimBusca;

      // a ultima thread analisa ate o final da lista
      if (i == (numberOfThreads - 1)) {
        fimBusca = todasCabines.size() - 1;
      } else {
        // o -1 serve para fim nao chocar com inicio de outra
        fimBusca = inicioBusca + quantidadeCabinesParaAnalise - 1;
      }

      Runnable r = new OrganizadorCabines(todasCabines, cabinesStudio, cabinesInside,
          cabinesOceanView, cabinesBalcony, inicioBusca, fimBusca);
      Thread t = new Thread(r);
      t.start();
      threads.add(t);

    }

    for (final Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    
  }

  public List<Cabine> pegarListaCabinesStudioDisponiveis() {
    System.out.println("tamanho da lista de cabinesStudio:" + cabinesStudio.size() + "de 25");
    for (Cabine cabine : cabinesStudio) {
      if(cabine.getId()==null || cabine == null) {
        System.out.println("situacao da cabineS"+cabine);
        cabine.setId(99999);
      }
    }
    return this.cabinesStudio;
  }

  public List<Cabine> pegarListaCabinesBalconyDisponiveis() {
    System.out.println("tamanho da lista de cabinesBalcony:" + cabinesBalcony.size() + "de 25");
    for (Cabine cabine : cabinesStudio) {
      if(cabine.getId()==null || cabine == null) {
        System.out.println("situacao da cabineB"+cabine);
        cabine.setId(99999);
      }
    }
    return this.cabinesBalcony;
  }

  public List<Cabine> pegarListaCabinesInsideDisponiveis() {
    System.out.println("tamanho da lista de cabinesInside:" + cabinesInside.size() + " de 120");
    
    for (Cabine cabine : cabinesStudio) {
      if(cabine.getId()==null || cabine == null) {
        System.out.println("situacao da cabineI"+cabine);
        cabine.setId(99999);
      }
    }
    
    return this.cabinesInside;
  }

  public List<Cabine> pegarListaCabinesOceanViewDisponiveis() {
    System.out
        .println("tamanho da lista de cabinesOceanView:" + cabinesOceanView.size() + "de 140");
    
    for (Cabine cabine : cabinesStudio) {
      if(cabine.getId()==null || cabine == null) {
        System.out.println("situacao da cabineO"+cabine);
        cabine.setId(99999);
      }
    }
    return this.cabinesOceanView;
  }

  public synchronized void marcarCabineComoIndisponivel(Reserva reserva, int idPacote)
      throws BDException {
    List<Cabine> todasCabines = repositorio.pegarPeloId(idPacote).getNavio();


    for (Cabine cabine : todasCabines) {
      if (reserva.getCabineBalcony() == cabine.getId()) {
        cabine.setDisponivel(false);
      } else if (reserva.getCabineInside() == cabine.getId()) {
        cabine.setDisponivel(false);
      } else if (reserva.getCabineOceanView() == cabine.getId()) {
        cabine.setDisponivel(false);
      } else if (reserva.getCabineStudio() == cabine.getId()) {
        cabine.setDisponivel(false);
      }
    }


    repositorio.pegarPeloId(idPacote).setNavio(todasCabines);


  }
  
  public synchronized void marcarCabineComoDisponivel(Reserva reserva, int idPacote)
      throws BDException {
    List<Cabine> todasCabines = repositorio.pegarPeloId(idPacote).getNavio();


    for (Cabine cabine : todasCabines) {
      if (reserva.getCabineBalcony() == cabine.getId()) {
        cabine.setDisponivel(true);
      } else if (reserva.getCabineInside() == cabine.getId()) {
        cabine.setDisponivel(true);
      } else if (reserva.getCabineOceanView() == cabine.getId()) {
        cabine.setDisponivel(true);
      } else if (reserva.getCabineStudio() == cabine.getId()) {
        cabine.setDisponivel(true);
      }
    }


    repositorio.pegarPeloId(idPacote).setNavio(todasCabines);


  }

}
