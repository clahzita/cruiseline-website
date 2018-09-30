package br.com.cruiseline.webapi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cruiseline.entities.Pacote;
import br.com.cruiseline.webapi.dao.PacoteDao;

@Service
public class PacoteService {
  
  @Autowired
  private PacoteDao repositorio;
  
  public List<Pacote> listarTodos() {
      return repositorio.listarTodos();
  }
  
}
