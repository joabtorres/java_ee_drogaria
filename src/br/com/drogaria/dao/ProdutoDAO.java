package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.factory.ConexaoFactory;

public class ProdutoDAO {
	public void salvar(Produto produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produto ");
		sql.append("(descricao, preco, quantidade, fabricante_codigo) ");
		sql.append("VALUES (?, ?, ?, ?)");

		Connection conexao = ConexaoFactory.getConnection();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, produto.getDescricao());
		comando.setDouble(2, produto.getPreco());
		comando.setLong(3, produto.getQuantidade());
		comando.setLong(4, produto.getFabricante().getCodigo());

		comando.executeUpdate();
	}

	public void editar(Produto produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produto ");
		sql.append("SET descricao = ?, preco = ?, quantidade = ?, fabricante_codigo = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.getConnection();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, produto.getDescricao());
		comando.setDouble(2, produto.getPreco());
		comando.setLong(3, produto.getQuantidade());
		comando.setLong(4, produto.getFabricante().getCodigo());

		comando.setLong(5, produto.getCodigo());
		comando.executeUpdate();
	}

	public void excluir(Produto produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.getConnection();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, produto.getCodigo());
		comando.executeUpdate();
	}

	public ArrayList<Produto> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT produto.codigo, produto.descricao, produto.quantidade, produto.preco, produto.fabricante_codigo, fabricante.descricao ");
		sql.append("FROM drogaria.produto, drogaria.fabricante ");
		sql.append("WHERE produto.fabricante_codigo=fabricante.codigo");

		Connection conexao = ConexaoFactory.getConnection();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Produto> lista = new ArrayList<Produto>();

		while (resultado.next()) {
			Produto produto = new Produto();
			produto.setCodigo(resultado.getLong("codigo"));
			produto.setDescricao(resultado.getString("descricao"));
			produto.setPreco(resultado.getDouble("preco"));
			produto.setQuantidade(resultado.getLong("quantidade"));
			Fabricante fabricante = new Fabricante();
			fabricante.setCodigo(resultado.getLong("fabricante_codigo"));
			fabricante.setDescricao(resultado.getString("fabricante.descricao"));
			produto.setFabricante(fabricante);

			lista.add(produto);
		}
		return lista;
	}

	public Produto buscarPorProduto(Produto produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT produto.codigo, produto.descricao, produto.quantidade, produto.preco, produto.fabricante_codigo, fabricante.descricao ");
		sql.append("FROM drogaria.produto, drogaria.fabricante ");
		sql.append("WHERE produto.fabricante_codigo=fabricante.codigo AND produto.codigo = ? ;");

		Connection conexao = ConexaoFactory.getConnection();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, produto.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Produto produtoResult = null;

		if (resultado.next()) {

			produtoResult = new Produto();
			produtoResult.setCodigo(resultado.getLong("codigo"));
			produtoResult.setDescricao(resultado.getString("descricao"));
			produtoResult.setPreco(resultado.getDouble("preco"));
			produtoResult.setQuantidade(resultado.getLong("quantidade"));
			Fabricante fabricante = new Fabricante();
			fabricante.setCodigo(resultado.getLong("fabricante_codigo"));
			fabricante.setDescricao(resultado.getString("fabricante.descricao"));
			produtoResult.setFabricante(fabricante);
		}
		return produtoResult;
	}
}
