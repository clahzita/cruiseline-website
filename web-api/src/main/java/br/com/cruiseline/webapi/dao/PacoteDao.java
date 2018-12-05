/**
 * 
 */
package br.com.cruiseline.webapi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cruiseline.webapi.entities.Cabine;
import br.com.cruiseline.webapi.entities.LocalDeck;
import br.com.cruiseline.webapi.entities.Pacote;
import br.com.cruiseline.webapi.entities.TipoCabine;
import br.com.cruiseline.webapi.exceptions.BDException;


/**
 * @author clah
 *
 */
@Component
public class PacoteDao implements GenericDAO<Pacote> {
  private static PacoteDao instance = new PacoteDao();
  
  private AtomicInteger sequence = new AtomicInteger(0);
  private List<Pacote> banco = new ArrayList<>();
  
  private PacoteDao() {  }
  
  public static PacoteDao getInstance() {
    return instance;
  }
  
  @PostConstruct
  public void iniciar() {
    Pacote pacote1 = new Pacote();
    pacote1.setMaximo(310);
    pacote1.setCapacidade(310);
    pacote1.setNome("Noronha");
    pacote1.setNavio(preencherCabines());
    pacote1.setPrecoMinimo(1500);
    this.salvar(pacote1);
    
    Pacote pacote2 = new Pacote();
    pacote2.setMaximo(310);
    pacote2.setCapacidade(310);
    pacote2.setNome("Europa");
    pacote2.setNavio(preencherCabines());
    pacote2.setPrecoMinimo(1000);
    this.salvar(pacote2);
    
    Pacote pacote3 = new Pacote();
    pacote3.setMaximo(310);
    pacote3.setCapacidade(310);
    pacote3.setNome("Caribe");
    pacote3.setNavio(preencherCabines());
    pacote3.setPrecoMinimo(1800);
    this.salvar(pacote3);
  }
  
  private List<Cabine> preencherCabines() {
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

  @Override
  public void salvar(Pacote novo) {
    novo.setId(sequence.getAndIncrement());
    synchronized (this) {
      banco.add(novo);
    }
    
  }

  @Override
  public void alterar(Pacote alterado, int id) throws BDException {
    for (Pacote pacote : banco) {
      if(pacote.getId() == id) {
        BeanUtils.copyProperties(alterado, pacote);
        System.out.println("Pacote editado com sucesso!");
        return;
      }
    }
    throw new BDException("Edição não realizada, pois Id ("+id+")do pacote não encontrado!");
    
  }

  @Override
  public void remover(int id) throws BDException {
    for (Pacote pacote : banco) {
      if(pacote.getId() == id) {
        synchronized (pacote) {
          banco.remove(pacote);
        }
        System.out.println("Pacote removido com sucesso!");
        return;
      }
    }
    throw new BDException("Remoçao não realizada, pois Id ("+id+")do pacote não encontrado!");
  }

  @Override
  public List<Pacote> listarTodos() {
    return banco;
  }

  @Override
  public Pacote pegarPeloId(int id) throws BDException {
    for (Pacote pacote : banco) {
      if(pacote.getId() == id) {
        return pacote;
      }
    }
    throw new BDException("Nenhum pacote com id "+id+" encontrado!");
   
  }
  

}
