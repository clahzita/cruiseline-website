package com.cruiseline.apirest.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cruiseline.apirest.models.Cabine;
import com.cruiseline.apirest.models.Pacote;
import com.cruiseline.apirest.services.CabineService;
import com.cruiseline.apirest.services.PacoteService;

@RestController
@RequestMapping(value="/apicruiseline")
public class PacoteResource {
  
  @Autowired
  PacoteService pacoteService;
  
  @Autowired
  CabineService cabineService;
  
  /**
   * Lista todos os pacotes de cruzeiros salvos.
   * @return lista de pacotes
   */
  @GetMapping("/pacotes")
  public List<Pacote> listaPacotes(){
    return pacoteService.findAll();
  }
  
  /**
   * Retorna um pacote especifico.
   * @param id identificador do pacote
   * @return pacote com id, caso contrário, null.
   * 
   */
  @GetMapping("/pacote/{id}")
  public Pacote listaPacoteUnico(@PathVariable(value="id") int id){
    return pacoteService.findById(id);
  }
  
  /**
   * Retorna todas as cabines que tem no pacote.
   * @param id identificador do pacote
   * @return Lista de cabines do pacote
   */
  @GetMapping("/pacote/{id}/cabines")
  public List<Cabine> listaCabineDoPacote(@PathVariable(value="id") int id){
    return pacoteService.findById(id).getNavio();
  }
  
  /**
   * Retorna determinada cabine do pacote
   * @param idPacote identificador do pacote
   * @param idCabine identificador da cabine
   * @return cabine especifica do pacote
   */
  @GetMapping("/pacote/{idPacote}/cabines/{idCabine}")
  public Cabine listaCabineUnicaDoPacote(@PathVariable(value="idPacote") int idPacote, @PathVariable(value="idCabine") int idCabine){
    return pacoteService.findById(idPacote).getNavio().get(idCabine);
  }
  
  /**
   * Atualiza valores dos atributos de uma determinada cabine do pacote.
   * @param idPacote identificador do pacote
   * @param cabine objeto com valores da cabine a serem alterados.
   * @return cabine com valores alterados.
   */
  @PutMapping("pacote/{idPacote}/cabine")
  public Cabine atualizarProduto(@PathVariable(value="idPacote") int idPacote, @RequestBody Cabine cabine) {
    
    return cabineService.save(idPacote,cabine);
  }

}
