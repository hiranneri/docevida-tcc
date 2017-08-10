package br.com.doceVida.controller.pedido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.doceVida.dao.ProdutoDAO;
import br.com.doceVida.model.Produto;



@ManagedBean(name="SelecaoProdutoBean")
@ViewScoped
public class SelecaoProdutoBean implements Serializable {
	

	private String valor;
	
	private static final long serialVersionUID = 1L;
	private List<Produto>produtosPesquisados;
	public  ProdutoDAO service;
	private int numeroRegistrosLocalizados;

	public SelecaoProdutoBean() {
		// TODO Auto-generated constructor stub
		this.numeroRegistrosLocalizados=0;
		
	}
	
	public List<Produto> getProdutosPesquisados() {
		return produtosPesquisados;
	}
	public void setListaProdutosPesquisados(List<Produto> produtos) {
		this.produtosPesquisados = produtos;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public int getNumeroRegistrosLocalizados() {
		return this.numeroRegistrosLocalizados;
	}
	public void setNumeroRegistrosLocalizados(int numeroRegistrosLocalizados) {
		this.numeroRegistrosLocalizados = numeroRegistrosLocalizados;
	}
	public  void pesquisarProduto(){
		produtosPesquisados = new ArrayList<>();
		this.service = new ProdutoDAO();
		setListaProdutosPesquisados(service.findByName(this.valor));
		this.setNumeroRegistrosLocalizados(this.getProdutosPesquisados().size());
	}
	
	public String selecionar(Produto produto){
		RequestContext.getCurrentInstance().closeDialog(produto);
		return "efetuar-pedido.xhtml?faces-redirect=true";

	}
	
}
