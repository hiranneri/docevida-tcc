package br.com.doceVida.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.doceVida.dao.ClienteDAO;
import br.com.doceVida.dao.DAOGenerico;
import br.com.doceVida.model.Cliente;


@WebServlet("/EditarClienteServlet")
public class EditarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Cliente cliente;
    private DAOGenerico<Cliente> clienteDAO = new ClienteDAO();
    HttpSession session;
    public EditarClienteServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);
		String id = request.getParameter("id");
		cliente = clienteDAO.findById(id);
		session.setAttribute("cliente", cliente);

		
		System.out.println("nome: "+cliente.getNome());
			
		request.getRequestDispatcher("editar-cliente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		
		request.setCharacterEncoding("UTF-8");
	
		String nome=request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String cnpj = request.getParameter("cnpj");
		String endereco = request.getParameter("endereco");
		String num =request.getParameter("numero");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String tel = request.getParameter("telefone");
		String cel  = request.getParameter("celular");
		String email = request.getParameter("email");
		Cliente clienteAlterado = new Cliente();
		System.out.println("ID "+cliente.getId());
		
		clienteAlterado.setId(cliente.getId());
		clienteAlterado.setNome(nome);
		clienteAlterado.setCpf(cpf);
		clienteAlterado.setCnpj(cnpj);
		clienteAlterado.setEndereco(endereco);
		if(num.equals("") || num==null){
			num = "0";
			
		}
		clienteAlterado.setNumero(Integer.parseInt((num)));
		clienteAlterado.setBairro(bairro);
		clienteAlterado.setCidade(cidade);
		clienteAlterado.setEstado(estado);
		clienteAlterado.setTelefone(tel);
		clienteAlterado.setCelular(cel);
		clienteAlterado.setEmail(email);
		
		DAOGenerico<Cliente> clienteDAO = new ClienteDAO();
		clienteDAO.editar(clienteAlterado);
		request.getRequestDispatcher("home.jsp").forward(request, response);
		
		
	}

}
