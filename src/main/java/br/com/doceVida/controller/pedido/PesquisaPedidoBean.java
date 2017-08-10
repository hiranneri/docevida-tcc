package br.com.doceVida.controller.pedido;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import br.com.doceVida.dao.PedidoDAO;
import br.com.doceVida.model.Pedido;

@ManagedBean(name="PesquisaPedidoBean")

public class PesquisaPedidoBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pedido pedido;
	private String id;
	private Date dataInicio;
	private Date dataFim;
	private Date dataAtual = new Date();
	private int numeroRegistrosLocalizados=0;
	private List<Pedido> pedidosPesquisados;
	
	public PesquisaPedidoBean(){
		this.pedido = new Pedido();
		numeroRegistrosLocalizados =0;
		this.pedidosPesquisados = new ArrayList<>();
		
	}
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
	
	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}
	

	public int getNumeroRegistrosLocalizados() {
		return this.pedidosPesquisados.size();
	}

	public void setNumeroRegistrosLocalizados(int numeroRegistrosLocalizados) {
		this.numeroRegistrosLocalizados = numeroRegistrosLocalizados;
	}
	
	public List<Pedido> getPedidosPesquisados() {
		return pedidosPesquisados;
	}

	public void setPedidosPesquisados(List<Pedido> pedidosPesquisados) {
		this.pedidosPesquisados = pedidosPesquisados;
	}

	public void pesquisarPedido(){
		PedidoDAO buscarPedido = new PedidoDAO();
		this.setPedidosPesquisados(buscarPedido.buscarPorData(this.dataInicio,this.dataFim));	
		
	}
	public void paginaInicial(){
		try{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext external = facesContext.getExternalContext();
			external.redirect("home.jsp");
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
}
}
