package cruzeiro.br.builders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cruzeiro.br.models.Cabine;
import cruzeiro.br.models.Navio;
import cruzeiro.br.models.Pacote;
import cruzeiro.br.models.TipoCabine;

public class GeradorPacotes {


	public Pacote criarPacote(Integer sequencia) {

		return new Pacote(sequencia, getLocalPartida(sequencia), 0.0, this.criarNavio(sequencia), LocalDate.now().plusDays(sequencia%365));

	}

	private String getLocalPartida(Integer sequencia) {
		
		return "Lugar Lindo "+sequencia;
	}

	private Navio criarNavio(Integer id) {

		Integer maximoCabines = getMaximoCabines();
		List<Cabine> cabines = new ArrayList<>();

		preencherCabines(cabines,maximoCabines);

		return new Navio(id, cabines, maximoCabines, maximoCabines) ;

	}

	private void preencherCabines(List<Cabine> cabines, Integer maximoCabines) {

		for (int i = 0; i < maximoCabines; i++) {
			TipoCabine tipo = getTipoCabine();
			cabines.add(new Cabine(i+1,tipo ,true, tipo.getValor()));
		}

	}

	private TipoCabine getTipoCabine() {

		Random gerador = new Random();


		switch (gerador.nextInt(4)) {
		case 0: return TipoCabine.STUDIO; 			
		case 1: return TipoCabine.BALCONY; 			
		case 2: return TipoCabine.INSIDE; 			
		case 3: return TipoCabine.OCEANVIEW; 			
		default: return TipoCabine.STUDIO;
		}

	}

	private Integer getMaximoCabines() {
		Random gerador = new Random();

		switch (gerador.nextInt(5)) {
		case 0: return 350; 			
		case 1: return 400; 			
		case 2: return 450; 			
		case 3: return 500; 
		case 4: return 530;
		default: return 300;
		}

	}

}
