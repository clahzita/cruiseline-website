package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import models.PacoteInfo;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.pacotes.listar;

public class Pacotes extends Controller {

	private static final String CASSADRA_URL = "127.0.0.1";
	private static final String KEY_SPACE_NAME = "mykeyspace";

	public static Result listar() {
		List<PacoteInfo> pacotes = new ArrayList<>();

		Cluster cluster = null;
		try {
			// Creating Cluster object
			cluster = Cluster.builder().addContactPoint(CASSADRA_URL).build();

			// Creating Session object
			Session session = cluster.connect(KEY_SPACE_NAME);

			// Query
			String selectCql = "SELECT * FROM pacote_info";

			// Getting the ResultSet
			ResultSet result = session.execute(selectCql);
			
			//Recomendado usar iterator() or successive calls to one() 
			//Para grande volume, usar iterator
			for (Row row : result) {
				pacotes.add(new PacoteInfo(row.getInt("pacote_id"), row.getString("pacote_dia_partida"),
						row.getString("pacote_local_partida"), row.getDouble("pacote_menor_preco")));
			}
			


		} finally {
			if (cluster != null) {
				System.out.println("fechando o cluster");
				cluster.close();

			}
		}

		return ok(listar.render(pacotes));
	}

	public static Result novoPacote() {

		return TODO;
	}

	// Show a product edit form
	public static Result detalhes(Integer ean) {
		return TODO;
	}

	// Save a product
	public static Result salvar() {
		return TODO;
	}

}
