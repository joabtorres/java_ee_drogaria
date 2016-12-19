package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUTIL;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	private Fabricante fabricante;
	private ArrayList<Fabricante> itens;
	private ArrayList<Fabricante> itensFiltrados;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	public ArrayList<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}

	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	/* roda antes de exibir a tela */
	@PostConstruct
	public void preparaPesquisa() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			this.setItens(dao.listar());
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUTIL.adicionarMensagemErro(e.getMessage());
		}
	}

	public void preparaNovo() {
		this.setFabricante(new Fabricante());
	}

	public void novo() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.salvar(this.getFabricante());

			this.setItens(dao.listar());

			JSFUTIL.adicionarMensagemSucesso("Cadastro realizado com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUTIL.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void excluir() {

		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.excluir(this.getFabricante());

			this.setItens(dao.listar());
			JSFUTIL.adicionarMensagemSucesso("Fabricante excluido com sucesso!");
		} catch (SQLException e) {
			JSFUTIL.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}


	public void editar() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.editar(fabricante);

			this.setItens(dao.listar());

			JSFUTIL.adicionarMensagemSucesso("Fabricante editado com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUTIL.adicionarMensagemErro(ex.getMessage());
		}

	}
}
