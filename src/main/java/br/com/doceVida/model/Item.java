package br.com.doceVida.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private Produto produto;
	private Pedido pedido;
	private int quantidade;
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	private BigDecimal subTotal;
	public Item(){
		this.quantidade =1;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidadeItem) {
		this.quantidade = quantidadeItem;
	}
	
	public BigDecimal getSubTotal() {
		//this.valorUnitario*this.quantidadeItem;
		BigDecimal quantidade = new BigDecimal(getQuantidade());
		
		this.subTotal = this.produto.getValorUnitario().multiply(quantidade);
		
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

		
	



}
