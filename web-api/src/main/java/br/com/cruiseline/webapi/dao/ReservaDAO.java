package br.com.cruiseline.webapi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import br.com.cruiseline.entities.Reserva;
import br.com.cruiseline.exceptions.BDException;

@Component
public class ReservaDAO implements GenericDAO<Reserva> {
  private AtomicInteger sequence = new AtomicInteger(0);

  private List<Reserva> banco = new ArrayList<>();
  
  @PostConstruct
  public void iniciar() {
   
  }
  
  @Override
  public void salvar(Reserva novo) {
    novo.setId(sequence.getAndIncrement());
    banco.add(novo);
    
  }

  @Override
  public void alterar(Reserva reservaAlterada, int id) throws BDException {
    for (Reserva reserva : banco) {
      if(reserva.getId() == id) {
        reserva.setUsuario(reservaAlterada.getUsuario());
        reserva.setPacote(reservaAlterada.getPacote());
        reserva.setNumeroPassageiros(reservaAlterada.getNumeroPassageiros());
        reserva.setCustoTotal(reservaAlterada.getCustoTotal());
        reserva.setCabinesSelecionadas(reservaAlterada.getCabinesSelecionadas());
        
        System.out.println("Editado com sucesso!");
        return;
      }
    }
    throw new BDException("Id n�o encontrado!");
    
  }

  @Override
  public void remover(int id) throws BDException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<Reserva> listarTodos() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Reserva pegarPeloId(int id) throws BDException {
    // TODO Auto-generated method stub
    return null;
  }

}
