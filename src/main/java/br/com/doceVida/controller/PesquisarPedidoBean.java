package br.com.doceVida.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.doceVida.dao.ClienteDAO;
import br.com.doceVida.dao.PedidoDAO;
import br.com.doceVida.model.Cliente;
import br.com.doceVida.model.Pedido;

@ViewScoped
@ManagedBean(name="SelecaoClienteBean")
public class PesquisarPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Pedido>pedidosLocalizados;
	private boolean isCliente;
	private String cliente;
	private boolean isData;
	private Date dataInicio;
	private Date dataFim;
	private boolean isPedido;
	private String idDoPedido;
	
	private PedidoDAO service;
	private int numeroRegistrosLocalizados;
	
	public PesquisarPedidoBean(){
	
		this.numeroRegistrosLocalizados = 0;
	}
	
		
	public List<Pedido> getPedidosLocalizados() {
		return pedidosLocalizados;
	}


	public void setPedidosLocalizados(List<Pedido> pedidosLocalizados) {
		this.pedidosLocalizados = pedidosLocalizados;
	}


	public boolean isCliente() {
		return isCliente;
	}


	public void setCliente(boolean isCliente) {
		this.isCliente = isCliente;
	}


	public String getCliente() {
		return cliente;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public boolean isData() {
		return isData;
	}


	public void setData(boolean isData) {
		this.isData = isData;
	}


	public Date getDataInicio() {
		return dataInicio;
	}


	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}


	public Date getDataFim() {
		return dataFim;
	}


	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}


	public boolean isPedido() {
		return isPedido;
	}


	public void setPedido(boolean isPedido) {
		this.isPedido = isPedido;
	}


	public String getIdDoPedido() {
		return idDoPedido;
	}


	public void setIdDoPedido(String idDoPedido) {
		this.idDoPedido = idDoPedido;
	}


	public int getNumeroRegistrosLocalizados() {
		return this.pedidosLocalizados.size();
	}

	public void setNumeroRegistrosLocalizados(int numeroRegistrosLocalizados) {
		this.numeroRegistrosLocalizados = numeroRegistrosLocalizados;
	}

	public void pesquisarPedidos(){
		System.out.println("pesquisando...");
		pedidosLocalizados = new ArrayList<>();
		this.service = new PedidoDAO();
		if(this.isCliente && this.isData){
			
			setPedidosLocalizados(service.pesquisarPorClienteEData(this.cliente,this.dataInicio,this.dataFim));
		}else if(this.isCliente){
			setPedidosLocalizados(service.pesquisarPorCliente(this.cliente));
		}else{
			setPedidosLocalizados(service.pesquisarPorData(this.dataInicio,this.dataFim));
		}
		
		this.setNumeroRegistrosLocalizados(this.getNumeroRegistrosLocalizados());
		for(Pedido pedido:this.pedidosLocalizados){
			System.out.println(pedido.getProduto().getNmProduto());
		}
		
	}
	public void selecionar(Pedido pedido){
		RequestContext.getCurrentInstance().closeDialog(pedido);
	}
	
}
