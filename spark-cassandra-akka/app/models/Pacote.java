package models;

import java.time.LocalDate;

public class Pacote {

	private Integer id;
	public String localPartida;
	public Double menorPreco;
	public Navio navio;  
	public LocalDate diaPartida;

	public Pacote() {

	}

	public Pacote(Integer id, String localPartida, Double menorPreco, Navio navio, LocalDate diaPartida) {
		super();
		this.id = id;
		this.localPartida = localPartida;
		this.menorPreco = menorPreco;
		this.navio = navio;
		this.diaPartida = diaPartida;
	}
	
	public Pacote(String localPartida, Double menorPreco, Navio navio, LocalDate diaPartida) {
		this.localPartida = localPartida;
		this.menorPreco = menorPreco;
		this.navio = navio;
		this.diaPartida = diaPartida;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocalPartida() {
		return localPartida;
	}

	public void setLocalPartida(String localPartida) {
		this.localPartida = localPartida;
	}

	public Double getMenorPreco() {
		return menorPreco;
	}

	public void setMenorPreco(Double menorPreco) {
		this.menorPreco = menorPreco;
	}

	public Navio getNavio() {
		return navio;
	}

	public void setNavio(Navio navio) {
		this.navio = navio;
	}

	public LocalDate getDiaPartida() {
		return diaPartida;
	}

	public void setDiaPartida(LocalDate diaPartida) {
		this.diaPartida = diaPartida;
	}

	
}
