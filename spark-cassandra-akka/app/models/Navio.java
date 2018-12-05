package models;

import java.time.LocalDate;
import java.util.List;

public class Navio {
	
	private int id;
	
	private List<Cabine> todasCabines;
	
	private Integer quantidadeCabines;
	
	private Integer cabinesDisponiveis;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Cabine> getTodasCabines() {
		return todasCabines;
	}

	public void setTodasCabines(List<Cabine> todasCabines) {
		this.todasCabines = todasCabines;
	}

	public Integer getQuantidadeTotalCabines() {
		return quantidadeCabines;
	}

	public void setQuantidadeCabines(Integer quantidadeCabines) {
		this.quantidadeCabines = quantidadeCabines;
	}

	public Integer getCabinesDisponiveis() {
		return cabinesDisponiveis;
	}

	public void setCabinesDisponiveis(Integer cabinesDisponiveis) {
		this.cabinesDisponiveis = cabinesDisponiveis;
	}
	
	
	
	

}
