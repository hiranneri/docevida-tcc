package br.com.doceVida.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.doceVida.dao.DAOGenerico;
import br.com.doceVida.dao.ProdutoDAO;
import br.com.doceVida.model.Produto;

/**
 * Servlet implementation class ExcluirProdutoServlet
 */
@WebServlet("/ExcluirProdutoServlet")
public class ExcluirProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOGenerico<Produto> daoProduto = new ProdutoDAO();    
   
    public ExcluirProdutoServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("id");
		if(id.equals("")|| id==null){
			id="0";
		}
		int id2 = Integer.parseInt(id);
		//ProdutoDAO excluirid = new ProdutoDAO();
		daoProduto.excluir(id2);
		request.getRequestDispatcher("pesquisar-produtos.jsp").forward(request, response);
	}

	
	}

