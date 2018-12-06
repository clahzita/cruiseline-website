package models;

public class PacoteInfo {
	
	public Integer id;
	public String diaPartida;
	public String localPartida;
	public Double menorPreco;
	
	public PacoteInfo(Integer id, String diaPartida, String localPartida, Double menorPreco) {
		super();
		this.id = id;
		this.localPartida = localPartida;
		this.diaPartida = diaPartida;
		this.menorPreco = menorPreco;
	}	

	
}

