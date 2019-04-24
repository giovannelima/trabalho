package br.com.trabalho.estacionamento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexaoBD {
	Estacionamento parking = new Estacionamento();
	PreparedStatement stmt = null;
	Connection con = null;
	boolean verifica = false;

	// insere no banco
	public int insert(Estacionamento insere) throws ClassNotFoundException, SQLException {

		Connection con = this.getConnection();

		String sql = "insert into estacionamento.carros (car_modelo, car_marca, car_cor, car_placa)"
				+ " values (?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, insere.getModelo());
		ps.setString(2, insere.getMarca());
		ps.setString(3, insere.getCor());
		ps.setString(4, insere.getPlaca());
		int ret = ps.executeUpdate();

		if (ret == 1) {
			verifica = true;
			System.out.println("Os dados foram salvos com sucesso.!");
		} else {

			System.out.println("Ocorreu um erro ao salvar os dados.! ");
		}
		con.close();
		return ret;
	}
	

	public void deletar(int del_id) throws ClassNotFoundException {

		try {
			con = this.getConnection();
			String sql = "  delete from estacionamento.carros where car_id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, del_id);
			int rec = stmt.executeUpdate();

			if (rec == 1) {

				verifica = true;
				System.out.println("Dados escluidos com sucesso.!");
			} else {
				System.out.println("Os dados não foram excluidos, favor verificar.!!");
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("Erro:" + e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		// return verifica;
	}

	// verifica se o ID existe dentro de banco, se sim retorna TRUE para
	// estacionamento teste
	public boolean verUpdate(int rec_id) throws ClassNotFoundException, SQLException {

		con = this.getConnection();
		try {
			stmt = con.prepareStatement(" Select * from  estacionamento.carros where car_id= ?");
			stmt.setInt(1, rec_id);
			ResultSet buscar = stmt.executeQuery();
			while (buscar.next()) {
				verifica = true;
				int idPark = buscar.getInt("car_id");
				String modeloPark = buscar.getString("car_modelo");
				String marcaPark = buscar.getString("car_marca");
				String corPark = buscar.getString("car_cor");
				String placaPark = buscar.getString("car_placa");

				System.out.println("||ID: " + idPark + "|" + "Modelo: " + modeloPark + "|" + "Marca: " + marcaPark + "|"
						+ "Cor: " + corPark + "|" + "Placa: " + placaPark + "||");
			}
			// if (verifica) {
			// System.out.println("\nOs dados foram encontrados.!!\n");
			// } else {
			// System.out.println("Os dados não foram encontrados.!");
			// }

			buscar.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("ERRO" + e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return verifica;
	}

	// faz o update no banco de um dado especifico, que "inserir" recebe
	public void update(String inserir, String recebe, int id) throws ClassNotFoundException, SQLException {

		con = this.getConnection();

		String sql = "update estacionamento.carros set " + inserir + " = ? where car_id = ?";
		stmt = con.prepareStatement(sql);

		try {
			stmt.setString(1, recebe);
			stmt.setInt(2, id);

			stmt.executeUpdate();

			int ret = stmt.executeUpdate();

			if (ret == 1) {
				verifica = true;
				System.out.println("Os dados foram alterados com sucesso.!");
			} else {

				System.out.println("Ocorreu um erro ao alterar os dados.! ");
			}
			con.close();
		} catch (SQLException e) {
			System.err.println("ERRO" + e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	// faz a busca do Id dentro dno banco e imprime
	public void selectID(int sel_id) throws ClassNotFoundException, SQLException {

		con = this.getConnection();

		try {

			String sql = "select * from  estacionamento.carros  where car_id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, sel_id);

			ResultSet buscar = stmt.executeQuery();
			while (buscar.next()) {
				verifica = true;
				int idPark = buscar.getInt("car_id");
				String modeloPark = buscar.getString("car_modelo");
				String marcaPark = buscar.getString("car_marca");
				String corPark = buscar.getString("car_cor");
				String placaPark = buscar.getString("car_placa");

				System.out.println("\n||ID: " + idPark + "|" + "Modelo: " + modeloPark + "|" + "Marca: " + marcaPark
						+ "|" + "Cor: " + corPark + "|" + "Placa: " + placaPark + "||\n");
				System.out.println("\nOs dados foram encontrados.!!\n");
			}
			if (verifica) {
				System.out.println("Os dados não foram encontrados.!");
			}

			buscar.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("ERRO" + e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	// busca no banco e imprime
	public void seleclLike(String modelo, String marca, String cor, String placa)
			throws ClassNotFoundException, SQLException {

		con = this.getConnection();

		try {

			String sql = "select * from  estacionamento.carros where car_modelo like ? or car_marca like ? or car_cor like ? or car_placa like ?";
			stmt = con.prepareStatement(sql);

			stmt.setString(1, "%" + modelo + "%");
			stmt.setString(2, "%" + marca + "%");
			stmt.setString(3, "%" + cor + "%");
			stmt.setString(4, "%" + placa + "%");

			ResultSet buscar = stmt.executeQuery();

			while (buscar.next()) {

				verifica = true;
				int idPark = buscar.getInt("car_id");
				String modeloPark = buscar.getString("car_modelo");
				String marcaPark = buscar.getString("car_marca");
				String corPark = buscar.getString("car_cor");
				String placaPark = buscar.getString("car_placa");

				System.out.println("||ID: " + idPark + "|" + "Modelo: " + modeloPark + "|" + "Marca: " + marcaPark + "|"
						+ "Cor: " + corPark + "|" + "Placa: " + placaPark + "||");

			}
			if (verifica) {

				System.out.println("\nOs dados foram encontrados.!");

			} else {

				System.out.println("\nOs dados não foram encontrados.!");

			}

			buscar.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("ERRO" + e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	// imprime tudo no banco
	public void selectAll() throws ClassNotFoundException, SQLException {
		boolean verifica = true;

		try {
			con = this.getConnection();
			stmt = con.prepareStatement(" Select * from  estacionamento.carros ");
			ResultSet buscar = stmt.executeQuery();
			while (buscar.next()) {
				verifica = false;
				int idPark = buscar.getInt("car_id");
				String modeloPark = buscar.getString("car_modelo");
				String marcaPark = buscar.getString("car_marca");
				String corPark = buscar.getString("car_cor");
				String placaPark = buscar.getString("car_placa");

				System.out.println("||ID: " + idPark + "|" + "Modelo: " + modeloPark + "|" + "Marca: " + marcaPark + "|"
						+ "Cor: " + corPark + "|" + "Placa: " + placaPark + "||");

			}
			if (!verifica) {
				System.out.println("\nOs dados foram encontrados.!!\n");
			} else {
				System.out.println("Os dados não foram encontrados.!");
			}

			buscar.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("ERRO" + e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	// cria conexão com o banco
	public Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/estacionamento", "root", "");

		return conn;
	}
}
