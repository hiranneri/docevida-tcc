package br.com.doceVida.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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
		for(Produto c:this.produtosPesquisados){
			System.out.println(c.getId());
		}
		
	}
	
	public String selecionar(Produto produto){
		System.out.println("O item selecionado foi:"+ produto.getNmProduto());
		System.out.println("Valor Unitário "+ produto.getValorUnitario());
		RequestContext.getCurrentInstance().closeDialog(produto);
		return "efetuar-pedido.xhtml?faces-redirect=true";

	}
	
}
