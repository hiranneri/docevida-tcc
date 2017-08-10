package br.com.doceVida.controller.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.doceVida.dao.LoginDAO;
import br.com.doceVida.model.Usuario;

/**
 * Servlet implementation class CadastroUsuarioServlet
 */
@WebServlet("/CadastroUsuarioServlet")
public class CadastroUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		Usuario user = new Usuario(nome,senha);
		LoginDAO daoLogin = new LoginDAO();
		daoLogin.cadastraUsuario(user);
		HttpSession session = request.getSession(true);
		session.setAttribute("nomeUsuario", user.getNome());
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
