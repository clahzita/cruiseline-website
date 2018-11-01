package br.com.cruiseline.webapi.concurrent;

import java.util.List;
import br.com.cruiseline.entities.Cabine;

public class OrganizadorCabines implements Runnable {

  private List<Cabine> todasCabines;
  private List<Cabine> cabinesStudio;
  private List<Cabine> cabinesInside;
  private List<Cabine> cabinesOceanView;
  private List<Cabine> cabinesBalcony;
  private int inicioBusca, fimBusca;


  public OrganizadorCabines(List<Cabine> todasCabines, List<Cabine> cabinesStudio,
      List<Cabine> cabinesInside, List<Cabine> cabinesOceanView, List<Cabine> cabinesBalcony,
      int inicioBusca, int fimBusca) {
    this.todasCabines = todasCabines;
    this.cabinesStudio = cabinesStudio;
    this.cabinesInside = cabinesInside;
    this.cabinesOceanView = cabinesOceanView;
    this.cabinesBalcony = cabinesBalcony;
    this.inicioBusca = inicioBusca;
    this.fimBusca = fimBusca;
  }

  @Override
  public void run() {
    for (int i = inicioBusca; i <= fimBusca; i++) {
      //seleciona as cabines disponiveis
      if(todasCabines.get(i).isDisponivel()) {
        //organiza por tipo
        this.organizarCabinePorTipo(todasCabines.get(i));
      }      
    }
  }

  private void organizarCabinePorTipo(Cabine cabine) {
    if(!cabine.isDisponivel()) {
      return;
    }
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
      default:
        break;
    }

  }


}
