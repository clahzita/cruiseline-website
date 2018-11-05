package com.cruiseline.apirest.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cruiseline.apirest.models.Pacote;
import com.cruiseline.apirest.services.PacoteService;

@RestController
@RequestMapping(value="/apicruiseline")
public class PacoteResource {
  
  @Autowired
  PacoteService pacoteService;
  
  @GetMapping("/pacotes")
  public List<Pacote> listaPacotes(){
    return pacoteService.findAll();
  }
  
  @GetMapping("/pacote/{id}")
  public Pacote listaPacoteUnico(@PathVariable(value="id") int id){
    return pacoteService.findById(id);
  }
  
  

}
