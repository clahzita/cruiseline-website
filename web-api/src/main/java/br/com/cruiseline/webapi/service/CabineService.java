package br.com.cruiseline.webapi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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
  
  
  public void organizarCabinesPorTipoExecutor(int idPacote) throws BDException, BusinessException {

    cabinesOceanView = new ArrayList<>();
    cabinesBalcony = new ArrayList<>();
    cabinesStudio = new ArrayList<>();
    cabinesInside = new ArrayList<>();
    
    int poolOfThreads = 8;
    int numberOfTasks = 20;

    List<Cabine> todasCabines = repositorio.pegarPeloId(idPacote).getNavio();

    if (todasCabines.isEmpty() || todasCabines == null) {
      throw new BusinessException("o navio não foi instanciado no pacote com id " + idPacote);
    }

    // definição de quantas cabines cada thread vai analisar
    int quantidadeCabinesParaAnalise = todasCabines.size() / numberOfTasks;

    ExecutorService executorService = Executors.newFixedThreadPool(poolOfThreads);
    
    for (int i = 0; i < numberOfTasks; i++) {

      int inicioBusca = i * quantidadeCabinesParaAnalise;
      int fimBusca;

      // a ultima thread analisa ate o final da lista
      if (i == (numberOfTasks - 1)) {
        fimBusca = todasCabines.size() - 1;
      } else {
        // o -1 serve para fim nao chocar com inicio de outra
        fimBusca = inicioBusca + quantidadeCabinesParaAnalise - 1;
      }
      
      

      Runnable r = new OrganizadorCabines(todasCabines, cabinesStudio, cabinesInside,
          cabinesOceanView, cabinesBalcony, inicioBusca, fimBusca);
      executorService.execute(r);

    }

    executorService.shutdown();

    
  }

  public void organizarCabinesPorTipoExecutorSchedule(int idPacote) throws BDException, BusinessException {

    cabinesOceanView = new ArrayList<>();
    cabinesBalcony = new ArrayList<>();
    cabinesStudio = new ArrayList<>();
    cabinesInside = new ArrayList<>();
    
    int poolOfThreads = 8;
    int numberOfTasks = 20;

    List<Cabine> todasCabines = repositorio.pegarPeloId(idPacote).getNavio();

    if (todasCabines.isEmpty() || todasCabines == null) {
      throw new BusinessException("o navio não foi instanciado no pacote com id " + idPacote);
    }

    // definição de quantas cabines cada thread vai analisar
    int quantidadeCabinesParaAnalise = todasCabines.size() / numberOfTasks;
    
    //Executor agendado
    ScheduledExecutorService fixedScheduledExecutorService =
        Executors.newScheduledThreadPool(poolOfThreads);
    
    for (int i = 0; i < numberOfTasks; i++) {

      int inicioBusca = i * quantidadeCabinesParaAnalise;
      int fimBusca;

      // a ultima thread analisa ate o final da lista
      if (i == (numberOfTasks - 1)) {
        fimBusca = todasCabines.size() - 1;
      } else {
        // o -1 serve para fim nao chocar com inicio de outra
        fimBusca = inicioBusca + quantidadeCabinesParaAnalise - 1;
      }
      
      

      Runnable r = new OrganizadorCabines(todasCabines, cabinesStudio, cabinesInside,
          cabinesOceanView, cabinesBalcony, inicioBusca, fimBusca);
      fixedScheduledExecutorService.scheduleAtFixedRate(r, 0, 2, TimeUnit.SECONDS);

    }
    
  }

  
  public List<Cabine> pegarListaCabinesStudioDisponiveis() {
    System.out.println("tamanho da lista de cabinesStudio:" + cabinesStudio.size() + "de 25");

//    Collections.sort(cabinesStudio);
    return this.cabinesStudio;
  }

  public List<Cabine> pegarListaCabinesBalconyDisponiveis() {
    System.out.println("tamanho da lista de cabinesBalcony:" + cabinesBalcony.size() + "de 25");
   
//    Collections.sort(cabinesBalcony);
    return this.cabinesBalcony;
  }

  public List<Cabine> pegarListaCabinesInsideDisponiveis() {
    System.out.println("tamanho da lista de cabinesInside:" + cabinesInside.size() + " de 120");
 //   Collections.sort(cabinesInside);   
    return this.cabinesInside;
  }

  public List<Cabine> pegarListaCabinesOceanViewDisponiveis() {
    System.out
        .println("tamanho da lista de cabinesOceanView:" + cabinesOceanView.size() + "de 140");
    
   // Collections.sort(cabinesOceanView);
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
