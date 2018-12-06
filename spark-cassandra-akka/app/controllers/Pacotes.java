package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.Navio;
import models.Pacote;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.pacotes.*;


public class Pacotes extends Controller{
	
		

		public static Result listar() {
			List<Pacote> pacotes = new ArrayList<>();
			
			pacotes.add(new Pacote(1, "Noronha", 1400.00, new Navio(), LocalDate.now()));
			
			return ok(listar.render(pacotes));
		}

		public static Result novoPacote() {
			
			return TODO;
		}
		
		//Show a product edit form
		public static Result detalhes(Integer ean) {
			return TODO;
		}
		
		//Save a product
		public static Result salvar() {
			return TODO;
		}
		
}
