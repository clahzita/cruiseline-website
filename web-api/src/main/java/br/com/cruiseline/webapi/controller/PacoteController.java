package br.com.cruiseline.webapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.cruiseline.entities.Pacote;
import br.com.cruiseline.webapi.service.PacoteService;

@Controller
public class PacoteController {
  
  @Autowired
  private PacoteService service;
  
  @GetMapping("/")
  public ModelAndView findAll() {
      
      ModelAndView mv = new ModelAndView("/pacote");
      mv.addObject("pacotes", service.listarTodos());
      
      return mv;
  }
  
  @GetMapping("/add")
  public ModelAndView add(Pacote pacote) {
    
    ModelAndView mv = new ModelAndView("/reserva");
    mv.addObject("pacote", pacote);
    
    return mv;
}
}
