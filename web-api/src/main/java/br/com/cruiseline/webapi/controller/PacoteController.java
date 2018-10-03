package br.com.cruiseline.webapi.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.cruiseline.entities.Pacote;
import br.com.cruiseline.entities.Reserva;
import br.com.cruiseline.entities.Usuario;
import br.com.cruiseline.webapi.exceptions.BDException;
import br.com.cruiseline.webapi.exceptions.BusinessException;
import br.com.cruiseline.webapi.service.PacoteService;
import br.com.cruiseline.webapi.service.ReservaService;

@Controller
public class PacoteController {

  @Autowired
  private PacoteService pacoteService;

  @Autowired
  private ReservaService reservaService;

  @GetMapping("/")
  public ModelAndView findAll() {

    ModelAndView mv = new ModelAndView("/pacote");
    mv.addObject("pacotes", pacoteService.listarTodos());
    
    //pacoteService.listarPorPreco();

    return mv;
  }

  @GetMapping("/add/{id}")
  public ModelAndView add(@PathVariable("id") Integer id) throws BDException, BusinessException {

    ModelAndView mv = new ModelAndView("/reserva");
    Pacote pacote = pacoteService.find(id);
    mv.addObject("pacote", pacote);

    Reserva reserva = new Reserva();
    reserva.setPacote(pacote);
    reservaService.salvar(reserva);

    mv.addObject("reserva", reserva);
    mv.addObject("usuario",new Usuario());    

    return mv;
  }

  @PostMapping("/salvar/{id}")
  public String save(Reserva reserva, Usuario usuario, @PathVariable("id") Integer id) throws BDException, BusinessException {

    Reserva reservaBanco = reservaService.encontrarUm(id);
    
    
    int idPacote = reservaBanco.getPacote().getId();
    pacoteService.diminuirCapacidade(idPacote);
    
    BeanUtils.copyProperties(reserva, reservaBanco,"pacote");
    
    reservaBanco.setUsuario(usuario);
    
    reservaService.editar(reservaBanco, id);

    return "redirect:/list/"+idPacote;
  }

  @GetMapping("/list/{id}")
  public ModelAndView allReservas(@PathVariable("id") int id) throws BusinessException {

    ModelAndView mv = new ModelAndView("/reservas");
    mv.addObject("reservas", reservaService.buscarTodasReservasPorPacote(id));

    return mv;
  }
  
  @GetMapping("/delete/{id}")
  public String delete(@PathVariable("id") int id) throws BusinessException, BDException {
         
      int idPacote = reservaService.encontrarUm(id).getPacote().getId();
      reservaService.deletar(id);
      pacoteService.aumentarCapacidade(idPacote);
      
      return "redirect:/";
  }


}
