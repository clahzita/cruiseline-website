package cruzeiro.br;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import cruzeiro.br.builders.GeradorPacotes;
import cruzeiro.br.models.Pacote;

public class MainGeradorPacotes {

	public static void main(String[] args) {
		// Building a cluster
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		// Creating Session object
		Session session = cluster.connect("cruzeiro");

		GeradorPacotes geradorPacotes = new GeradorPacotes();

		// for (int i = 0; i < 30000000; ++i) {
		Integer i = 0;
		Pacote pacote = geradorPacotes.criarPacote(i);// i

		String queryPacoteInfo = gerarQueryPacoteInfo(pacote, i);
		System.out.println(queryPacoteInfo);
		// session.execute(queryPacoteInfo);

		String queryPacoteNavio = gerarQueryPacoteNavio(pacote, i);
		System.out.println(queryPacoteNavio);
		// session.execute(queryPacoteNavio);

		for (int j = 0; j < pacote.getNavio().getCabines().size(); j++) {
			String queryNavioCabine = gerarQueryNavioCabine(pacote, j);
			System.out.println(queryNavioCabine);
			// session.execute(queryNavioCabine);
		}

		cluster.close();
		session.close();

	}

	private static String gerarQueryNavioCabine(Pacote pacote, Integer cabine_id) {
		String query = "INSERT INTO navio_cabine ("
				+ "navio_id, cabine_numero, cabine_tipo, cabine_disponivel,cabine_preco)" 
				+ "VALUES ("
				+ pacote.getNavio().getId() + ", " + pacote.getNavio().getCabines().get(cabine_id).getNumero() + ", "
				+ pacote.getNavio().getCabines().get(cabine_id).getTipo().toString() + ", "
				+ pacote.getNavio().getCabines().get(cabine_id).isDisponivel() + ","
				+ pacote.getNavio().getCabines().get(cabine_id).getPreco() + ");";
		return query;
	}

	private static String gerarQueryPacoteNavio(Pacote pacote, Integer sequencia) {
		String query = "INSERT INTO pacote_navio ("
				+ "pacote_id, navio_id, navio_maximo, navio_disponiveis)" 
				+ "VALUES ("
				+ sequencia + ", " 
				+ pacote.getNavio().getId() + ", " 
				+ pacote.getNavio().getMaximo() + ", "
				+ pacote.getNavio().getDisponiveis() 
				+ ");";
		return query;
	}

	private static String gerarQueryPacoteInfo(Pacote pacote, Integer sequencia) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

		String query = "INSERT INTO pacote_info (pacote_id, local_partida, dia_partida, menor_preco)" + "VALUES ("
				+ sequencia + ", " + pacote.getLocalPartida() + ", " + formatter.format(pacote.getDiaPartida()) + ", "
				+ pacote.getMenorPreco() + ");";
		return query;
	}

}
