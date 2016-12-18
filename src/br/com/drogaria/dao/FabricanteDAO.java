package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {

	public void salvar(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante");
		sql.append(" (descricao) ");
		sql.append(" VALUES (?)");

		Connection conexao = ConexaoFactory.getConnection();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, fabricante.getDescricao());

		comando.executeUpdate();

	}

	public void editar(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.getConnection();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, fabricante.getDescricao());
		comando.setLong(2, fabricante.getCodigo());

		comando.executeUpdate();
	}

	public void excluir(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.getConnection();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, fabricante.getCodigo());

		comando.executeUpdate();
	}

	public Fabricante buscarPorCodigo(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ? ;");

		Connection conexao = ConexaoFactory.getConnection();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, fabricante.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Fabricante retorno = null;

		if (resultado.next()) {
			retorno = new Fabricante();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}

		return retorno;
	}

	public ArrayList<Fabricante> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY descricao ASC");

		Connection conexao = ConexaoFactory.getConnection();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante fabricante = new Fabricante();
			fabricante.setCodigo(resultado.getLong("codigo"));
			fabricante.setDescricao(resultado.getString("descricao"));
			lista.add(fabricante);
		}
		return lista;
	}

	public ArrayList<Fabricante> buscarPorDescricao(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");
		Connection conexao = ConexaoFactory.getConnection();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + fabricante.getDescricao() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante item = new Fabricante();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			lista.add(item);
		}
		return lista;
	}

	public  void mainX(String[] args) {
		/*
		 * Fabricante f1 = new Fabricante(); 
		 * Fabricante f2 = new Fabricante();
		 * f1.setDescricao("DESCRICAO 1"); 
		 * f2.setDescricao("Descricao 2");
		 * 
		 * FabricanteDAO fabricanteDAO = new FabricanteDAO();
		 * 
		 * try { 
		 * fabricanteDAO.salvar(f1); 
		 * fabricanteDAO.salvar(f2);
		 * System.out.println("Os fabricantes foram salvos com suceso"); 
		 * } catch (SQLException e) { 
		 * System.out.println("Ocorreu um erro ao tentar salvar um dos fabricantes");
		 * e.printStackTrace(); 
		 * }
		 */

		/*
		 * Fabricante f3 = new Fabricante(); f3.setCodigo(3L); FabricanteDAO
		 * fabricanteDAO = new FabricanteDAO();
		 * 
		 * try { fabricanteDAO.excluir(f3);
		 * System.out.println("Fabricante excluido com sucesso!"); } catch
		 * (SQLException e) {
		 * System.out.println("Fabricante NÃO foi excluido!");
		 * e.printStackTrace(); }
		 */

		/*
		 * Fabricante f4 = new Fabricante(); f4.setCodigo(2L);
		 * f4.setDescricao("DESCRIÇÃO 2 ATUALIZADA");
		 * 
		 * 
		 * FabricanteDAO fabricanteDAO = new FabricanteDAO(); try {
		 * fabricanteDAO.editar(f4);
		 * System.out.println("Fabricante atualizado com sucesso!"); } catch
		 * (SQLException e) {
		 * System.out.println("Ocorreu um erro ao tenta atualizar o fabricante"
		 * ); e.printStackTrace(); }
		 */

		/*
		 * Fabricante f5 = new Fabricante(); f5.setCodigo(2L);
		 * 
		 * Fabricante f6 = new Fabricante(); f6.setCodigo(4L);
		 * 
		 * FabricanteDAO fabricanteDAO = new FabricanteDAO(); 
		 * try { 
		 * Fabricante f7 = fabricanteDAO.buscarPorCodigo(f5);
		 *  Fabricante f8 = fabricanteDAO.buscarPorCodigo(f6);
		 * System.out.println("Fabricante Encosntrado");
		 * System.out.println("Resultado 1: " + f7);
		 * System.out.println("Resultado 2: " + f8); } catch (SQLException e) {
		 * System.out.println("Ocorreu na buscar pelo fabricante");
		 * e.printStackTrace(); }
		 */

		/*
		 * FabricanteDAO fabricanteDAO = new FabricanteDAO(); try {
		 * ArrayList<Fabricante> lista = fabricanteDAO.listar(); for (Fabricante
		 * fabricante : lista) { System.out.println(fabricante); } } catch
		 * (SQLException e) {
		 * System.out.println("Ocorreu um erro ao tentar lista os fabricantes");
		 * e.printStackTrace(); }
		 */

		Fabricante f1 = new Fabricante();
		f1.setDescricao("1");
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		try {
			ArrayList<Fabricante> lista = fabricanteDAO.buscarPorDescricao(f1);
			for(Fabricante fabricante : lista){
				System.out.println("Resultado: "+fabricante);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
