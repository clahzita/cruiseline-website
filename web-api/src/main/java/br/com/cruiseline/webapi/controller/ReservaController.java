//package br.com.cruiseline.webapi.controller;
//
//import javax.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//import br.com.cruiseline.entities.Reserva;
//import br.com.cruiseline.webapi.service.ReservaService;
//
//@Controller
//public class ReservaController {
//  
//  @Autowired
//  private ReservaService service;
//  
//  @GetMapping("/")
//  public ModelAndView findAll() {
//      
//      ModelAndView mv = new ModelAndView("/reserva");
//      mv.addObject("reservas", service.buscarTodos());
//      
//      return mv;
//  }
//  
//  @GetMapping("/add")
//  public ModelAndView add(Reserva reserva) {
//      
//      ModelAndView mv = new ModelAndView("/reserva");
//      mv.addObject("reserva", reserva);
//      
//      return mv;
//  }
//  
//  @GetMapping("/edit/{id}")
//  public ModelAndView edit(@PathVariable("id") int id) {
//      
//      return add(service.encontrarUm(id));
//  }
//  
//  @GetMapping("/delete/{id}")
//  public ModelAndView delete(@PathVariable("id") int id) {
//      
//      service.deletar(id);
//      
//      return findAll();
//  }
//
//  @PostMapping("/save")
//  public ModelAndView save(@Valid Reserva reserva, BindingResult result) {
//      
//      if(result.hasErrors()) {
//          return add(reserva);
//      }
//      
//      service.salvar(reserva);
//      
//      return findAll();
//  }
//  
//}
