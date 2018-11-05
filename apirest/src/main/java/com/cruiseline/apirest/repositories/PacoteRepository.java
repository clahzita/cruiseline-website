package com.cruiseline.apirest.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Component;
import com.cruiseline.apirest.models.Cabine;
import com.cruiseline.apirest.models.LocalDeck;
import com.cruiseline.apirest.models.Pacote;
import com.cruiseline.apirest.models.TipoCabine;

@Component
public class PacoteRepository {

  private static PacoteRepository instance = new PacoteRepository();

  private AtomicInteger sequence = new AtomicInteger(0);

  private List<Pacote> banco = new ArrayList<>();

  private PacoteRepository() { iniciar(); }

  public void iniciar() {
    Pacote pacote1 = new Pacote();
    pacote1.setId(0);
    pacote1.setMaximo(310);
    pacote1.setCapacidade(310);
    pacote1.setNome("Noronha");
    pacote1.setNavio(fieldCabines());
    pacote1.setPrecoMinimo(1500);
    this.save(pacote1);
    
    Pacote pacote2 = new Pacote();
    pacote2.setId(1);
    pacote2.setMaximo(310);
    pacote2.setCapacidade(310);
    pacote2.setNome("Europa");
    pacote2.setNavio(fieldCabines());
    pacote2.setPrecoMinimo(1000);
    this.save(pacote2);
    
    Pacote pacote3 = new Pacote();
    pacote3.setId(2);
    pacote3.setMaximo(310);
    pacote3.setCapacidade(310);
    pacote3.setNome("Caribe");
    pacote3.setNavio(fieldCabines());
    pacote3.setPrecoMinimo(1800);
    this.save(pacote3);
  }
  
  public static PacoteRepository getInstance() {
    return instance;
  }

  public List<Pacote> findAll(){
    return banco;
  }
  
  public void save(Pacote pacote){
    pacote.setId(sequence.getAndIncrement());
    banco.add(pacote);  
  }
  
  public Pacote findById(int id) {
    return banco.get(id);
  }

  private List<Cabine> fieldCabines() {
    
    List<Cabine> cabines = new ArrayList<>();
    
    for(int i = 0; i < 25;i++) {
      Cabine cabine = new Cabine(i+100, TipoCabine.STUDIO, LocalDeck.CENTRAL, 4, TipoCabine.STUDIO.getValor());
      cabines.add(cabine);
    }
    
    for(int i = 0; i < 40;i++) {
      Cabine cabine = new Cabine(i+200, TipoCabine.INSIDE, LocalDeck.CENTRAL, 4, TipoCabine.INSIDE.getValor());
      cabines.add(cabine);
    }
    
    for(int i = 0; i < 25;i++) {
      Cabine cabine = new Cabine(300+i, TipoCabine.BALCONY, LocalDeck.POPA, 4, TipoCabine.BALCONY.getValor());
      cabines.add(cabine);
    }
    
    for(int i = 0; i < 140;i++) {
      Cabine cabine = new Cabine(400+i, TipoCabine.OCEANVIEW, LocalDeck.PROA, 4, TipoCabine.OCEANVIEW.getValor());
      cabines.add(cabine);
    }
    
    for(int i = 40; i < 120;i++) {
      Cabine cabine = new Cabine(200+i, TipoCabine.INSIDE, LocalDeck.POPA, 4, TipoCabine.INSIDE.getValor());
      cabines.add(cabine);
    }
    
    System.out.println("tamanho da lista de cabines Navio:" + cabines.size());
    return cabines;
  }

}
