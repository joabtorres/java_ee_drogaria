package br.com.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTeste {

	public void salvar() throws SQLException {
		Produto produto = new Produto();
		produto.setDescricao("Produto 5");
		produto.setPreco(2.40D);
		produto.setQuantidade(10L);

		Fabricante fabricante = new Fabricante();
		fabricante.setCodigo(10L);
		produto.setFabricante(fabricante);

		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(produto);
	}

	public void editar() throws SQLException {
		Produto produto = new Produto();
		produto.setDescricao("Dipirona");
		produto.setPreco(5.40D);
		produto.setQuantidade(10L);
		produto.setCodigo(2L);

		Fabricante fabricante = new Fabricante();
		fabricante.setCodigo(1L);
		produto.setFabricante(fabricante);

		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(produto);
	}

	public void excluir() throws SQLException {
		Produto produto = new Produto();
		produto.setCodigo(2L);
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(produto);
	}

	public void lista() throws SQLException {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ArrayList<Produto> lista = produtoDAO.listar();
		for (Produto produto : lista) {
			System.out.println(produto);
		}
	}

	public void buscarPorCodigo() throws SQLException {
		Produto produto = new Produto();
		produto.setCodigo(3L);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto p2 = produtoDAO.buscarPorProduto(produto);
		System.out.println(p2);
	}
}
