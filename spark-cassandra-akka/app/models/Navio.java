package models;

import java.util.List;

public class Navio {
	
	private Integer id;
	
	private List<Cabine> cabines;
	
	private Integer maximo;
	
	private Integer disponiveis;
	
	

	public Navio(Integer id, List<Cabine> cabines, Integer maximo, Integer disponiveis) {
		super();
		this.id = id;
		this.cabines = cabines;
		this.maximo = maximo;
		this.disponiveis = disponiveis;
	}

	public Navio() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Cabine> getCabines() {
		return cabines;
	}

	public void setCabines(List<Cabine> cabines) {
		this.cabines = cabines;
	}

	public Integer getMaximo() {
		return maximo;
	}

	public void setMaximo(Integer maximo) {
		this.maximo = maximo;
	}

	public Integer getDisponiveis() {
		return disponiveis;
	}

	public void setDisponiveis(Integer disponiveis) {
		this.disponiveis = disponiveis;
	}

	
	
	
	

}
