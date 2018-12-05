package models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Pacote;
import models.dao.PacoteDao;
import models.dao.ReservaDAO;
import models.exception.BDException;
import models.exception.BusinessException;

@Service
public class PacoteService {

	private static PacoteService instance = new PacoteService();

	private PacoteService() {	}
	
	public static PacoteService getInstance() {
		return instance;
	}

	@Autowired
	private PacoteDao repositorio;


	public List<Pacote> listarTodos() {
		return repositorio.listarTodos();
	}

	public Pacote buscar(Integer id) throws BDException {
		return repositorio.pegarPeloId(id);
	}

	public synchronized int diminuirQuantidadeCabinesDisponiveis(int idPacote, int qtdeCabinesSelecionadas)
			throws BDException, BusinessException {

		Pacote pacote = repositorio.pegarPeloId(idPacote);
		Integer capacidade;

		capacidade = pacote.getNavio().getCabinesDisponiveis();
		Integer capacidadeAtualizada = capacidade - qtdeCabinesSelecionadas;
		if (capacidade < 0) {
			throw new BusinessException("Capacidade não pode ser menor que zero.");
		}
		pacote.getNavio().setCabinesDisponiveis(capacidadeAtualizada);
		repositorio.alterar(pacote, idPacote);

		return capacidade;
	}

	public synchronized int aumentarQuantidadeCabinesDisponiveis(int idPacote, int qtdeCabinesExcluidas)
			throws BDException, BusinessException {

		Pacote pacote = repositorio.pegarPeloId(idPacote);
		Integer capacidade;

		capacidade = pacote.getNavio().getCabinesDisponiveis();
		Integer capacidadeAtualizada = capacidade + qtdeCabinesExcluidas;
		if (capacidade > pacote.getNavio().getQuantidadeTotalCabines()) {
			throw new BusinessException("Capacidade não pode ser maior que o máximo.");
		}
		pacote.getNavio().setCabinesDisponiveis(capacidadeAtualizada);
		repositorio.alterar(pacote, idPacote);

		return capacidade;
	}

	public void alterar(Pacote pacote, int id) throws BDException {
		repositorio.alterar(pacote, id);

	}

	public Pacote pegarPeloId(int pacoteId) throws BDException {
		return repositorio.pegarPeloId(pacoteId);
	}


}
