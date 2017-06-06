package br.com.doceVida.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Pedido implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date dataPedido;
	private Date dataEntrega;
	private Cliente cliente;
	private Produto produto;
	private List<Item>itensDoPedido = new ArrayList<>();
	private BigDecimal valorTotal;
	
	public Pedido(){
		this.valorTotal = BigDecimal.ZERO;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataPedido()    {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Item> getItensDoPedido() {
		return itensDoPedido;
	}
	public void setItensDoPedido(List<Item> itensDoPedido) {
		this.itensDoPedido = itensDoPedido;
	}
	public BigDecimal getValorTotal() {
		return calcularValorTotal();
	}
	public BigDecimal getValorPedido(){
		return this.valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public void novoItem(Produto produto, int quantidadeItem){
		
		Item item = new Item();
		item.setProduto(produto);
		item.setPedido(this);
		item.setQuantidade(quantidadeItem);
		this.getItensDoPedido().add(item);
		
	}
	public BigDecimal calcularValorTotal(){
		
		BigDecimal valorTotalParcial= BigDecimal.ZERO;
		for(Item itemPedido :this.getItensDoPedido()){
			 valorTotalParcial=valorTotalParcial.add(itemPedido.getSubTotal());
			
		}
		
		return valorTotalParcial;
			
	}
	public boolean isValorTotalNegativo(){
		return this.valorTotal.compareTo(BigDecimal.ZERO)<0;
	}
	
	public void excluirItem(Item itemSelecionado){
		
		for(Item item:this.itensDoPedido){
			if(itemSelecionado.getCodigo() == item.getCodigo()){
				this.itensDoPedido.remove(itemSelecionado);
				System.out.println("Excluiu");
				break;
			}
		}
		atualizarValorTotal(itemSelecionado);
	}
	private void atualizarValorTotal(Item itemExcluido){
		
		this.valorTotal = this.valorTotal.subtract(itemExcluido.getSubTotal());
		
	}

}
