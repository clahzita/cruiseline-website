package br.com.cruiseline.webapi.controller;

import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.cruiseline.entities.Pacote;
import br.com.cruiseline.exceptions.BDException;
import br.com.cruiseline.webapi.dao.PacoteDao;

@RestController("/cruiser")
public class CruiserController {

  @Autowired
  private PacoteDao dao;
  
  @GetMapping("/pacotes")
  public List<Pacote> listarPacotes() {
    List<Pacote> lista = null;
    lista = dao.listarTodos();
    
    if(lista.isEmpty()||lista == null) {
      System.out.println("lista vazia");
    }
    return lista;
  }

  @PostMapping("/pacotes")
  public String adicionarPacote(Pacote pacote) {

    System.out.println(pacote.getNome());
    
    dao.salvar(pacote);
    
    return "Salvo com Sucesso";
  }

  @GetMapping("/pacotes/{id}")
  public Pacote buscarPorId(@PathParam("id") int idPacote) {
    Pacote pacote = null;

    try {
      pacote = dao.pegarPeloId(idPacote);
      
    } catch (BDException e) {
      System.out.println("pacote nao encontrado");
      e.printStackTrace();
    }
    
    return pacote;
  }
  
  @PutMapping("/pacotes/{id}")
  public String editarNota(Pacote pacote, @PathParam("id") int id) {
      String msg = "";
       
      System.out.println(pacote.getNome());
       
      try {
          dao.alterar(pacote, id);
           
          msg = "Pacote editadao com sucesso!";
      } catch (BDException e) {
          msg = "Erro ao editar a nota!";
          e.printStackTrace();
      }
       
      return msg;
  }
  
  @DeleteMapping("pacotes/{id}")
  public String removerNota(@PathParam("id") int id) {
    String msg = "";
     
    try {
        dao.remover(id);
         
        msg = "Pacote removido com sucesso!";
    } catch (BDException e) {
        msg = "Erro ao remover a nota!";
        e.printStackTrace();
    }
     
    return msg;
}
  
}
