package br.com.doceVida.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.doceVida.dao.ClienteDAO;
import br.com.doceVida.model.Cliente;

@ViewScoped
@ManagedBean(name="SelecaoClienteBean")
public class SelecaoClienteBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Cliente>clientes;
	private String valorPesquisaCliente;
	private ClienteDAO service;
	private int numeroRegistrosLocalizados=0;
	
	public SelecaoClienteBean(){
	
		this.numeroRegistrosLocalizados = 0;
		this.valorPesquisaCliente="";
	}
	
	public List<Cliente> getClientes(){
		return clientes;
	}
	
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
		
	public String getValorPesquisaCliente() {
		return valorPesquisaCliente;
	}

	public void setValorPesquisaCliente(String valorPesquisaCliente) {
		this.valorPesquisaCliente = valorPesquisaCliente;
	}

	public int getNumeroRegistrosLocalizados() {
		return this.numeroRegistrosLocalizados;
	}

	public void setNumeroRegistrosLocalizados(int numeroRegistrosLocalizados) {
		this.numeroRegistrosLocalizados = numeroRegistrosLocalizados;
	}

	public void pesquisarCliente(){
		System.out.println("pesquisando...");
		clientes = new ArrayList<>();
		this.service = new ClienteDAO();
		setClientes(service.findByName(this.valorPesquisaCliente));
		this.setNumeroRegistrosLocalizados(this.getClientes().size());
		for(Cliente c:this.clientes){
			System.out.println(c.getEndereco());
		}
		
	}
	public void selecionar(Cliente cliente){
		System.out.println("O cliente selecionado foi:"+cliente.getNome());
		RequestContext.getCurrentInstance().closeDialog(cliente);
	}
	
}
