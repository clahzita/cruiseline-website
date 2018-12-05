package cruzeiro.br;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import cruzeiro.br.builders.GeradorPacotes;
import cruzeiro.br.models.Pacote;

public class Main {

	public static void main(String[] args) {
		//Building a cluster
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		//Creating Session object
		Session session = cluster.connect("cruzeiro");
		
		GeradorPacotes geradorPacotes = new GeradorPacotes();
		
		//for (int i = 0; i < 30000000; ++i) {
			Pacote pacote = geradorPacotes.criarPacote(0);
			System.out.println(pacote.getNavio().getCabines().size());
			
			String query1 = gerarQueryPacoteInfo(pacote);
			System.out.println(query1);
			//session.execute(query1);
		//}

		cluster.close();
		session.close();
		

	}

	private static String gerarQueryPacoteInfo(Pacote pacote) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

		String query = "INSERT INTO pacote_info (local_partida, dia_partida, menor_preco)"
		+"VALUES ("+pacote.getLocalPartida()+", "
				+formatter.format(pacote.getDiaPartida())+", "
						+ pacote.getMenorPreco()+");";
		return query;
	}

}
