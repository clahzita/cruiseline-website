package br.com.cruiseline.webapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cruiseline.webapi.concurrent.OrganizadorCabinesRunnable;
import br.com.cruiseline.webapi.concurrent.OrganizadorCabinesForkJoin;
import br.com.cruiseline.webapi.dao.PacoteDao;
import br.com.cruiseline.webapi.entities.Cabine;
import br.com.cruiseline.webapi.entities.Reserva;
import br.com.cruiseline.webapi.entities.TipoCabine;
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


  public void organizarCabinesPorTipoRunnable(int idPacote) throws BDException, BusinessException {

    List<Cabine> oceanView = new ArrayList<>();
    List<Cabine> balcony = new ArrayList<>();
    List<Cabine> studio = new ArrayList<>();
    List<Cabine> inside = new ArrayList<>();

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

      Runnable r = new OrganizadorCabinesRunnable(todasCabines, studio, inside,
          oceanView, balcony, inicioBusca, fimBusca);
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
    
    cabinesBalcony.addAll(balcony);
    balcony.clear();
    cabinesInside.addAll(inside);
    inside.clear();
    cabinesOceanView.addAll(oceanView);
    oceanView.clear();
    cabinesStudio.addAll(studio);
    studio.clear();

  }


  public void organizarCabinesPorTipoExecutor(int idPacote) throws BDException, BusinessException {

    List<Cabine> oceanView = new ArrayList<>();
    List<Cabine> balcony = new ArrayList<>();
    List<Cabine> studio = new ArrayList<>();
    List<Cabine> inside = new ArrayList<>();

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



      Runnable r = new OrganizadorCabinesRunnable(todasCabines, studio, inside,
          oceanView, balcony, inicioBusca, fimBusca);
      executorService.execute(r);

    }

    executorService.shutdown();
    
    synchronized (this) {
      cabinesBalcony.addAll(balcony);
    }
    balcony.clear();
    
    synchronized (this) {
    cabinesInside.addAll(inside);
    }
    inside.clear();
    
    synchronized (this) {
    cabinesOceanView.addAll(oceanView);
    }
    oceanView.clear();
    
    synchronized (this) {
    cabinesStudio.addAll(studio);
    }
    studio.clear();
    
  }

  public void organizarCabinesPorTipoExecutorSchedule(int idPacote)
      throws BDException, BusinessException {

    cabinesOceanView = new ArrayList<>();
    cabinesBalcony = new ArrayList<>();
    cabinesStudio = new ArrayList<>();
    cabinesInside = new ArrayList<>();

    int poolOfThreads = 8;
    int numberOfTasks = 20;

    List<Cabine> todasCabines = repositorio.pegarPeloId(idPacote).getNavio();

    if (todasCabines.isEmpty() || todasCabines == null) {
      throw new BusinessException("O navio não foi instanciado no pacote com id " + idPacote);
    }

    // definição de quantas cabines cada thread vai analisar
    int quantidadeCabinesParaAnalise = todasCabines.size() / numberOfTasks;

    // Executor agendado
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

      Runnable r = new OrganizadorCabinesRunnable(todasCabines, cabinesStudio, cabinesInside,
          cabinesOceanView, cabinesBalcony, inicioBusca, fimBusca);
      fixedScheduledExecutorService.scheduleAtFixedRate(r, 0, 2, TimeUnit.SECONDS);

    }
    
    //TODO precisa terminar?
    
  }

  public void organizarCabinesPorTipoForkJoin(Integer idPacote) throws BDException {

    List<Cabine> todasCabines = repositorio.pegarPeloId(idPacote).getNavio();

    ForkJoinPool pool = ForkJoinPool.commonPool();

    cabinesOceanView = new ArrayList<>();
    cabinesBalcony = new ArrayList<>();
    cabinesStudio = new ArrayList<>();
    cabinesInside = new ArrayList<>();

    OrganizadorCabinesForkJoin task = new OrganizadorCabinesForkJoin(todasCabines, cabinesStudio,
        cabinesInside, cabinesOceanView, cabinesBalcony);

    pool.invoke(task);
    
    //TODO devo invocar algum metodo para terminar?

  }

  public synchronized void organizarCabinesParallelStram(Integer idPacote) throws BDException {
    List<Cabine> todasCabines = repositorio.pegarPeloId(idPacote).getNavio();
    
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

    List<Cabine> todasCabines = reserva.getPacote().getNavio();

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

    repositorio.pegarPeloId(reserva.getPacote().getId()).setNavio(todasCabines);

    return qtdeCabinesDisponiveisSelecionadas;

  }

  public synchronized int marcarCabineComoIndisponivel(Reserva reserva) throws BDException {

    List<Cabine> todasCabines = reserva.getPacote().getNavio();

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

    repositorio.pegarPeloId(reserva.getPacote().getId()).setNavio(todasCabines);

    return qtdeCabinesDisponiveisSelecionadas;


  }

  public synchronized int marcarCabineComoDisponivel(Reserva reserva) throws BDException {

    List<Cabine> todasCabines = reserva.getPacote().getNavio();

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

    repositorio.pegarPeloId(reserva.getPacote().getId()).setNavio(todasCabines);

    return qtdeCabinesDisponiveisSelecionadas;


  }




}
