package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.JSFUTIL;


@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {
	private Produto produto;
	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltrados;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}

	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void preparaPesquisa() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			this.setItens(dao.listar());
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUTIL.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void preparaNovo(){
		this.setProduto(new Produto());
		this.getProduto().setFabricante(new Fabricante());
	}
	
	public void novo(){
		try{
			ProdutoDAO dao = new ProdutoDAO();
			dao.salvar(this.getProduto());
			
			this.setItens(dao.listar());
			
			JSFUTIL.adicionarMensagemSucesso("Novo Produto Cadastrado");
			
		}catch(SQLException e){
			e.printStackTrace();
			JSFUTIL.adicionarMensagemErro(e.getMessage());
		}
	}
	public void excluir(){
		try{
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluir(this.getProduto());
			this.setItens(dao.listar());
			JSFUTIL.adicionarMensagemSucesso("Produto Excluido com sucesso! ");
		}catch(SQLException e){
			e.printStackTrace();
			JSFUTIL.adicionarMensagemErro(e.getMessage());
		}
	}
	public void editar(){
		try{
			ProdutoDAO dao = new ProdutoDAO();
			dao.editar(this.getProduto());
			
			this.setItens(dao.listar());
			
			JSFUTIL.adicionarMensagemSucesso("Produto Alterado com sucesso!");
		}catch(SQLException e){
			e.printStackTrace();
			JSFUTIL.adicionarMensagemErro(e.getMessage());
		}
	}
}
