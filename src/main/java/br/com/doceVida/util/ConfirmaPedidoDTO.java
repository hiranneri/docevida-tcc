package br.com.doceVida.util;


import java.time.LocalDate;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.doceVida.model.Cliente;
import br.com.doceVida.model.Pedido;

public class ConfirmaPedidoDTO {
	private Pedido pedido;
	private String dataDoPedido;
	private String dataDaEntrega;
	
	public Pedido getPedido() {
		Pedido pedido = recuperarPedido();
		return pedido;
	}
	
	public String getDataDoPedido() {
		return formatarLocalDate(getPedido().getDataEntrega());
	}

	public void setDataDoPedido(String dataDoPedido) {
		this.dataDoPedido = dataDoPedido;
	}

	public String getDataDaEntrega() {
		return formatarLocalDate(getPedido().getDataPedido());
	}

	public void setDataDaEntrega(String dataDaEntrega) {
		this.dataDaEntrega = dataDaEntrega;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	private String formatarLocalDate(LocalDate data){
		StringBuilder dataBR = new StringBuilder();
		dataBR.append(data.getDayOfMonth());
		dataBR.append("/");
		dataBR.append(data.getMonthValue());
		dataBR.append("/");
		dataBR.append(data.getYear());
		String data2 = dataBR.toString();
		return data2;
	}
	private static Pedido recuperarPedido(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		Pedido pedido = (Pedido) session.getAttribute("pedido");
		if(pedido.getCliente()==null){
			pedido.setCliente(Cliente.getClientePadrao());
		}
		return pedido; 
	}
}
