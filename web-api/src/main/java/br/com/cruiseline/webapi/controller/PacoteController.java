package br.com.cruiseline.webapi.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.cruiseline.webapi.entities.Pacote;
import br.com.cruiseline.webapi.entities.Reserva;
import br.com.cruiseline.webapi.entities.Usuario;
import br.com.cruiseline.webapi.exceptions.BDException;
import br.com.cruiseline.webapi.exceptions.BusinessException;
import br.com.cruiseline.webapi.service.CabineService;
import br.com.cruiseline.webapi.service.PacoteService;
import br.com.cruiseline.webapi.service.ReservaService;

@Controller
public class PacoteController {

  @Autowired
  private PacoteService pacoteService;

  @Autowired
  private ReservaService reservaService;

  @Autowired
  private CabineService cabineService;
  
  private List<String> erros;


  @GetMapping("/")
  public ModelAndView findAll() {

    ModelAndView mv = new ModelAndView("/pacote");
    mv.addObject("pacotes", pacoteService.listarTodos());
    
    //pacoteService.listarPorPreco();

    return mv;
  }

  @GetMapping("/add/{id}")
  public ModelAndView add(@PathVariable("id") Integer idPacote){
    
    erros = new ArrayList<>();
    ModelAndView mv = new ModelAndView("/reserva");
    Pacote pacote = null;
    Reserva reserva = new Reserva();
    
    try {
      pacote = pacoteService.pegarPeloId(idPacote);
      mv.addObject("pacote", pacote);
      reserva.setPacote(pacote);
      Usuario usuario = new Usuario();
      reserva.setUsuario(usuario);
      
    } catch (BDException e) {
      erros.add(e.getMessage());
    }
    
    try {
      reservaService.salvar(reserva);
      mv.addObject("reserva", reserva);
      mv.addObject("usuario",reserva.getUsuario()); 
    } catch (BDException e1) {
      erros.add(e1.getMessage());
    } catch (BusinessException e1) {
      erros.add(e1.getMessage());
    }
    
    
  
    try {
      synchronized (this) {
      //organizando exibição de cabines disponiveis no pacote
        //cabineService.organizarCabinesPorTipo(idPacote);
        //cabineService.organizarCabinesPorTipoExecutor(idPacote);
        //cabineService.organizarCabinesPorTipoExecutorSchedule(idPacote);
        //cabineService.organizarCabinesPorTipoForkJoin(idPacote);
        cabineService.organizarCabinesParallelStram(idPacote);
      }
        mv.addObject("cabinesDisponiveisBalcony", cabineService.pegarListaCabinesBalconyDisponiveis());
        mv.addObject("cabinesDisponiveisInside", cabineService.pegarListaCabinesInsideDisponiveis());
        mv.addObject("cabinesDisponiveisOceanView", cabineService.pegarListaCabinesOceanViewDisponiveis());
        mv.addObject("cabinesDisponiveisStudio", cabineService.pegarListaCabinesStudioDisponiveis());
      
      
    } catch (BDException e) {
      erros.add(e.getMessage());}
//    } catch (BusinessException e) {
//      erros.add(e.getMessage());
//    }
    
    mv.addObject("erros", erros);
    
    return mv;
  }

  @PostMapping("/salvar/{id}")
  public String save(Reserva reserva, @PathVariable("id") Integer idReserva){
    
    erros = new ArrayList<>();
    
    System.out.println("usuario reserva login: "+reserva.getUsuario().getLogin());
    System.out.println("usuario reserva id: "+reserva.getUsuario().getId());
    
    int idPacote = -1;
    Reserva reservaBanco;
    
    try {
      reservaBanco = reservaService.pegarPeloId(idReserva);
      idPacote = reservaBanco.getPacote().getId();
      BeanUtils.copyProperties(reserva, reservaBanco,"pacote");
      reservaBanco.setUsuario(reserva.getUsuario());
      reservaService.alterar(reservaBanco, idReserva);
      
    } catch (BDException | BusinessException e) {
      erros.add(e.getMessage());
    }    
    
    //TODO resolver o que fazer com os erros
    
    return "redirect:/list/"+idPacote;
  }

  @GetMapping("/list/{id}")
  public ModelAndView allReservas(@PathVariable("id") int idPacote) {
    erros = new ArrayList<>();
    
    ModelAndView mv = new ModelAndView("/reservas");
    try {
      mv.addObject("reservas", reservaService.buscarTodasReservasPorPacote(idPacote));
    } catch (BusinessException e) {
      erros.add(e.getMessage());
    }

    mv.addObject("erros", erros);
    
    return mv;
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable("id") int idReserva){
    erros = new ArrayList<>();
    
 //   int idPacote = reservaService.pegarPeloId(idReserva).getPacote().getId();
    try {
      reservaService.deletar(idReserva);
    } catch (BusinessException e) {
      erros.add(e.getMessage());
    } catch (BDException e) {
      erros.add(e.getMessage());
    }
    
    //TODO resolver como enviar erros para tela
   
    return "redirect:/";
  }


}
