package br.com.doceVida.controller.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.doceVida.dao.ClienteDAO;
import br.com.doceVida.dao.DAOGenerico;
import br.com.doceVida.model.Cliente;

/**
 * Servlet implementation class ExcluirClienteServlet
 */
@WebServlet("/ExcluirClienteServlet")
public class ExcluirClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DAOGenerico<Cliente> daoCliente = new ClienteDAO();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcluirClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nmCliente= request.getParameter("id");
		if(nmCliente.equals("")|| nmCliente==null){
			nmCliente="0";
		}
		int id = Integer.parseInt(nmCliente);
		daoCliente.excluir(id);
		request.getRequestDispatcher("pesquisar-clientes.jsp").forward(request, response);
	}

	
}
