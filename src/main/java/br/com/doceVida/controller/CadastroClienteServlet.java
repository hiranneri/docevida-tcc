package br.com.doceVida.controller;

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
 * Servlet implementation class CadastroClienteServlet
 */
@WebServlet("/CadastroClienteServlet")
public class CadastroClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOGenerico<Cliente> inserirCliente = new ClienteDAO();
   
    public CadastroClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Cliente novoCliente = new Cliente();
		//Nome,CPF e/ou CNPJ Endereço Número Bairro Cidade Estado Tel Cel Email
		String nome, endereco, bairro,cidade, estado, email, cpf, cnpj, tel, cel;
		int numeroCasa = 0;
		nome = request.getParameter("nome");
		endereco = request.getParameter("endereco");
		
		String num = request.getParameter("numero");
		if(num.equals("")|| num.equals(null)){
			num = "0";
		}
		numeroCasa = Integer.parseInt(num);
		
		bairro = request.getParameter("bairro");
		cidade = request.getParameter("cidade");
		estado = request.getParameter("estado");
		email = request.getParameter("email");
		cpf = request.getParameter("cpf");
		cnpj = request.getParameter("cnpj");
		tel = request.getParameter("telefone");
		cel = request.getParameter("celular");
		
		novoCliente.setNome(nome);
		novoCliente.setEndereco(endereco);
		novoCliente.setNumero(numeroCasa);
		novoCliente.setBairro(bairro);
		novoCliente.setCidade(cidade);
		novoCliente.setEstado(estado);
		novoCliente.setEmail(email);
		if(cpf.equals("")){
			novoCliente.setCnpj(cnpj);
		}else{
			novoCliente.setCpf(cpf);
		}
		
		novoCliente.setTelefone(tel);
		novoCliente.setCelular(cel);
		System.out.println("CPF "+novoCliente.getCpf()+"\n"+"CNPJ "+novoCliente.getCnpj());
		
		String resposta = inserirCliente.inserir(novoCliente);
		System.out.println(resposta);
		request.getRequestDispatcher("home.jsp").forward(request, response);
		
		
		
	}

}
