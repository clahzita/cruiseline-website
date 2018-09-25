package br.com.cruiseline.webapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.cruiseline.entities.Pacote;
import br.com.cruiseline.webapi.dao.PacoteDao;

@RestController("/cruiser")
public class CruiserController {

  @Autowired
  private PacoteDao dao;
  
  @GetMapping("/listar")
  public List<Pacote> getTodos(){
    return dao.listarTodos();
  }
  
}
