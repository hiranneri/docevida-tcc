package br.com.doceVida.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

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
	private int numeroRegistrosLocalizados=0;
	private List<String> parametros;
	private String[] selectedCities2;
	
	public PesquisarPedidoBean(){
	
		this.numeroRegistrosLocalizados = 0;
	}
	
	@PostConstruct
    public void init() {
        parametros = new ArrayList<String>();
        parametros.add("Cliente");
        parametros.add("Data");
        parametros.add("Pedido");
     }
		

/*
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
	*/
}
