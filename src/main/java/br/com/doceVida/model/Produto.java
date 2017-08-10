package br.com.doceVida.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class Produto implements Serializable {
    
	 
	private static final long serialVersionUID = 1L;
	private long id;
    private String nmProduto;
    private int quantidadeEstoque;
    private String tamanho;
    private BigDecimal valorUnitario = BigDecimal.ZERO;
    private String observacao;
   
	
	public Produto(){
		
	}
	public Produto(String nome,String tamanho,BigDecimal valor){
		this.nmProduto=nome;
		this.tamanho=tamanho;
		this.valorUnitario=valor;
	}
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNmProduto() {
		return nmProduto;
	}
	public void setNmProduto(String nmProduto) {
		this.nmProduto = nmProduto;
	}
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(int quant) {
		this.quantidadeEstoque = quant;
	}
	
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	
	
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public void diminuirEstoque(int quantidade){
		int novaQuantidade = this.getQuantidadeEstoque() - quantidade;
		this.setQuantidadeEstoque(novaQuantidade);
	}
	
	public void adicionarEstoque(int quantidade){
		this.quantidadeEstoque +=quantidade;
	}

    
    
    
    
    
    
}
