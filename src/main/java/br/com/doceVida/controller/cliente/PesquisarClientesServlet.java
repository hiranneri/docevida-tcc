package br.com.doceVida.controller.cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.doceVida.dao.ClienteDAO;
import br.com.doceVida.dao.DAOGenerico;
import br.com.doceVida.model.Cliente;


/**
 * Servlet implementation class PesquisarClienteServlet
 */
@WebServlet("/PesquisarClienteServlet")
public class PesquisarClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DAOGenerico<Cliente> dadosPesquisa = new ClienteDAO();   
    List<Cliente> clientesLocalizados = new ArrayList<>();
    //List<Paciente> pacientes = new ArrayList<>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesquisarClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String parametroPesquisa = "";
		if(request.getParameter("paramPesquisa").equalsIgnoreCase("Nome"))parametroPesquisa = "Nome";
		if(request.getParameter("paramPesquisa").equalsIgnoreCase("Endereço"))parametroPesquisa="Endereço";
		String dadoPesquisa = request.getParameter("valor");
		clientesLocalizados =  dadosPesquisa.listar(parametroPesquisa,dadoPesquisa);
		
		request.setAttribute("listaClientes", clientesLocalizados);
		request.setAttribute("resultadoPesquisa", clientesLocalizados.size());
		request.getRequestDispatcher("pesquisar-clientes.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
