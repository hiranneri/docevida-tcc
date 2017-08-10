package br.com.doceVida.controller.pedido;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.doceVida.model.Cliente;
import br.com.doceVida.model.Item;
import br.com.doceVida.model.Pedido;
import br.com.doceVida.model.Produto;

@ManagedBean(name="SessaoDePedido")
@SessionScoped
public class PedidoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	private Produto produto; 
	private Pedido pedido;
	private  int quantidadeDoItem;
	private LocalDate dataDaEntrega;
	private String dataAtual;
	private Date dataHoje = new Date();
	
	public PedidoBean(){
		this.pedido = new Pedido();
		
		
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getQuantidadeDoItem() {
		return 1;
	}

	public void setQuantidadeDoItem(int quantidadeDoItem) {
		this.quantidadeDoItem = quantidadeDoItem;
	}

	public LocalDate getDataDaEntrega() {
		return dataDaEntrega;
	}

	public void setDataDaEntrega(LocalDate dataDaEntrega) {
		this.dataDaEntrega = dataDaEntrega;
	}
	
	
	
	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}
	
	public String getDataAtual() {
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		return dataFormatada.format(this.dataHoje);
	}

	public Date getDataHoje() {
		return dataHoje;
	}

	public void setDataHoje(Date dataHoje) {
		this.dataHoje = dataHoje;
	}

	public void clienteSelecionado(SelectEvent event ){
		Cliente cliente = (Cliente) event.getObject();
		
		this.setCliente(cliente);
	}
	public String produtoSelecionado(SelectEvent event ){
		
		Produto produto = (Produto) event.getObject();
		this.setProduto(produto);
		return "efetuar-pedido?faces-redirect=true";
	}
	public void adicionarItems(){
		this.pedido.novoItem(this.produto,this.quantidadeDoItem);
		this.produto = null;
		this.quantidadeDoItem = 1;
	}
	public String excluirItem(Item itemSelecionado){
		this.pedido.excluirItem(itemSelecionado);
		return "efetuar-pedido?faces-redirect=true";
	}
	
	
	public void abrirDialogo(){
		Map<String, Object> opcoes = new HashMap<>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		
		RequestContext.getCurrentInstance().openDialog("selecao-cliente",opcoes,null);
	}
	public void abrirDialogoProduto(){
		
		Map<String, Object> opcoes = new HashMap<>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		
		RequestContext.getCurrentInstance().openDialog("selecao-produto",opcoes,null);
		
	}
	
	
	public String confirmarPedido(){
		this.pedido.setDataEntrega(dataDaEntrega);
		this.pedido.setCliente(cliente);
		this.pedido.setDataPedido(getDataFormatada());
		adicionarPedidoSessao();
		return "confirmar-pedido?faces-redirect=true"; 
	}
	private void adicionarPedidoSessao(){
		FacesContext.getCurrentInstance().getExternalContext().
		getSessionMap().put("pedido", this.pedido);
	}
	private LocalDate getDataFormatada(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    return LocalDate.parse(getDataAtual(),formatter);
	}

	
}
