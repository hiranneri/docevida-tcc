package br.com.doceVida.controller.pedido;

import java.io.IOException;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.doceVida.dao.PedidoDAO;
import br.com.doceVida.model.Pedido;

public class ConfirmaPedidoBean {
	private Pedido pedido;
	private String mensagem;
	
	public ConfirmaPedidoBean(){
		this.pedido = recuperarPedido();
	}
	public Pedido getPedido() {
		
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public void realizarPedido(){
		PedidoDAO novoPedido = new PedidoDAO();
		String mensagem = novoPedido.inserir(pedido);
		setMensagem(mensagem);
		redirecionar("mensagem-pedido.xhtml");
		destruirSessao();
	}
		
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public void inicio(){
		redirecionar("../home.jsp");
	}
	private void destruirSessao(){ 
		FacesContext.getCurrentInstance().getExternalContext().
			getSessionMap().remove("SessaoDePedido");
	}
	private Pedido recuperarPedido(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		Pedido pedido = (Pedido) session.getAttribute("pedido");
		if(pedido.getCliente().getId()==null){
			pedido.getCliente().setId(12L);
			pedido.getCliente().setNome("-");
		}
		return pedido; 
	}
	public void cancelarPedido(){
		destruirSessao();
		redirecionar("home.jsp");
	}
	private void redirecionar(String pagina){
		try{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext external = facesContext.getExternalContext();
			external.redirect(pagina);
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
	
}
