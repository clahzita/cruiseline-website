package br.com.cruiseline.webapi.concurrent;

import java.util.List;
import java.util.concurrent.RecursiveAction;
import br.com.cruiseline.entities.Cabine;

public class OrganizadorCabinesForkJoin extends RecursiveAction {

  private static final int SEQUENTIAL_THRESHOLD = 10;
  
  private List<Cabine> todasCabines;
  private List<Cabine> cabinesStudio;
  private List<Cabine> cabinesInside;
  private List<Cabine> cabinesOceanView;
  private List<Cabine> cabinesBalcony;



  public OrganizadorCabinesForkJoin(List<Cabine> todasCabines, List<Cabine> cabinesStudio,
      List<Cabine> cabinesInside, List<Cabine> cabinesOceanView, List<Cabine> cabinesBalcony) {
    this.todasCabines = todasCabines;
    this.cabinesStudio = cabinesStudio;
    this.cabinesInside = cabinesInside;
    this.cabinesOceanView = cabinesOceanView;
    this.cabinesBalcony = cabinesBalcony;

  }

  @Override
  protected void compute() {
    if (todasCabines.size() <= SEQUENTIAL_THRESHOLD) {

      this.organizarCabinePorTipo();

    } else {// caso recursivo
      // Calculate new range
      int mid = todasCabines.size() / 2;

      OrganizadorCabinesForkJoin firstSubtask =
          new OrganizadorCabinesForkJoin(todasCabines.subList(0, mid), cabinesStudio, cabinesInside,
              cabinesOceanView, cabinesBalcony);

      OrganizadorCabinesForkJoin secondSubtask =
          new OrganizadorCabinesForkJoin(todasCabines.subList(mid, todasCabines.size()),
              cabinesStudio, cabinesInside, cabinesOceanView, cabinesBalcony);

      //      firstSubtask.fork(); // queue the first task
      //      secondSubtask.compute(); // compute the second task
      //      firstSubtask.join(); // wait for the first task result
      invokeAll(firstSubtask, secondSubtask);
    }

  }

  private void organizarCabinePorTipo() {

    for (Cabine cabine : todasCabines) {
      if(cabine.isDisponivel()) {
        switch (cabine.getTipo()) {
          case STUDIO:
            synchronized (this) {
              cabinesStudio.add(cabine);
            }
            break;
          case INSIDE:
            synchronized (this) {
              cabinesInside.add(cabine);
            }
            break;
          case BALCONY:
            synchronized (this) {
              cabinesBalcony.add(cabine);
            }

            break;
          case OCEANVIEW:
            synchronized (this) {
              cabinesOceanView.add(cabine);
            }
            break;
          default: System.err.println("Cabine não possui tipo válido.");
            break;
        }
      }

    }

  }

}
