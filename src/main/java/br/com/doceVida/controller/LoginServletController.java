package br.com.doceVida.controller;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServletController")
public class LoginServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServletController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		
		
		Usuario usuario = new Usuario(nome,senha);
		LoginDAO login = new LoginDAO();
		boolean status = login.validarUsuario(usuario);
		HttpSession session = request.getSession(true);		
		if(status){
			if(usuario.getNome().equals("admin")&&usuario.getSenha().equals("admin")){
				request.getRequestDispatcher("cadastro_usuario.html").forward(request, response);
			}else{
				session.setAttribute("nomeUsuario", usuario.getNome());
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}	
		}else{
			request.getRequestDispatcher("erro-login.html").forward(request, response);
		}
	}

}
