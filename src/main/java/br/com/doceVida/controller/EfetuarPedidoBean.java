package br.com.doceVida.controller;


import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.doceVida.dao.PedidoDAO;
import br.com.doceVida.model.Cliente;
import br.com.doceVida.model.Item;
import br.com.doceVida.model.Pedido;
import br.com.doceVida.model.Produto;

@ManagedBean(name="EfetuarPedidoBean")
@SessionScoped
public class EfetuarPedidoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	private Produto produto; 
	private Pedido pedido;
	private  int quantidadeDoItem;
	private String strDataAtual;
	private String strDataDaEntrega;
	private Date dataDaEntrega;
	private Date dataAtual = new Date();
	
	public EfetuarPedidoBean(){
		this.pedido = new Pedido();
		this.quantidadeDoItem = 1;
		this.strDataAtual = dataSistema();
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
		
		//this.calcularValorTotal();
		
		this.produto = null;
		this.quantidadeDoItem = 1;
	}
	public String excluirItem(Item itemSelecionado){
		this.pedido.excluirItem(itemSelecionado);
		return "efetuar-pedido?faces-redirect=true";
	}
	
	
	public void abrirDialogo(){
		System.out.println("Método");
		Map<String, Object> opcoes = new HashMap<>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		
		RequestContext.getCurrentInstance().openDialog("SelecaoCliente",opcoes,null);
		System.out.println("janela");
	}
	public void abrirDialogoProduto(){
		
		Map<String, Object> opcoes = new HashMap<>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		
		RequestContext.getCurrentInstance().openDialog("SelecionaProduto",opcoes,null);
		
	}
	
	public void ajustaData(){
		this.dataDaEntrega = this.dataAtual;
	}
	//Método que captura a dataAtual do sistema     
	public String dataSistema(){
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		
		return dataFormatada.format(dataAtual);
	}
	public String getStrDataAtual() {
		return strDataAtual;
	}
	public void setStrDataAtual(String strDataAtual) {
		this.strDataAtual = strDataAtual;
	}
	public Date getDataDaEntrega() {
		return this.dataDaEntrega;
	}
	public void setDataDaEntrega(Date dataDaEntrega) {
		this.dataDaEntrega = dataDaEntrega;
	}

	public String getStrDataDaEntrega() {
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		
		return dataFormatada.format(getDataDaEntrega());
	}

	public void setStrDataDaEntrega(String strDataDaEntrega) {
		this.strDataDaEntrega = strDataDaEntrega;
	}

	public Date getDataAtual() {
		return dataAtual;
	}
	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	
	public String confirmarPedido(){
		this.pedido.setDataPedido(dataAtual);
		this.pedido.setDataEntrega(dataDaEntrega);
		if(this.cliente==null){
			Cliente clientePadrao = new Cliente();
			clientePadrao.setId(12);
			clientePadrao.setNome("-");
			this.pedido.setCliente(clientePadrao);
		}else{
			this.pedido.setCliente(cliente);
		}
	
		//Redirecionar para a página confirmarPedido.xhtml
		return "confirmar-pedido?faces-redirect=true"; 
	}
	public void redirecionar(String pagina){
		try{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext external = facesContext.getExternalContext();
			external.redirect(pagina);
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
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
	
	
	public int getQuantidadeDoItem() {
		return quantidadeDoItem;
	}

	public void setQuantidadeDoItem(int quantidadeDoItem) {
		this.quantidadeDoItem = quantidadeDoItem;
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	//ConfirmarPedido
	public void realizarNovoPedido(){
		//DAO
		PedidoDAO novoPedido = new PedidoDAO();
		novoPedido.inserir(this.pedido);
		mensagem();
		redirecionar("home.jsp");
		destruirSessao();
		
	}
	public void cancelarPedido(){
		
		redirecionar("home.jsp");
	}
	public void mensagem(){
		System.out.println("Executou mensagem");
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso","Pedido realizado com sucesso");
		RequestContext.getCurrentInstance().showMessageInDialog(mensagem);
	}
	public void destruirSessao(){ 
		System.out.println("Destruir sessão");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("EfetuarPedidoBean");
		
	}
}
