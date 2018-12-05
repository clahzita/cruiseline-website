package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.Navio;
import models.Pacote;
import models.dao.ReservaDAO;
import models.services.PacoteService;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.pacotes.listar;



public class Pacotes extends Controller{
	
		private static PacoteService pacoteService = PacoteService.getInstance();
		 
		private ReservaDAO repositorioReserva = ReservaDAO.getInstance();

		public static Result listar() {
			List<Pacote> pacotes = new ArrayList<>();
			
			pacotes.add(new Pacote(0, "NOronha", 100, new Navio(), LocalDate.now(), 5));
			
			return ok(listar.render(pacotes));
		}

		public static Result novoPacote() {
			
			return TODO;
		}
		
		//Show a product edit form
		public static Result detalhes(String ean) {
			return TODO;
		}
		
		//Save a product
		public static Result salvar() {
			return TODO;
		}
		
}
