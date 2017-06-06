package br.com.doceVida.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.doceVida.dao.DAOGenerico;
import br.com.doceVida.dao.ProdutoDAO;
import br.com.doceVida.model.Item;
import br.com.doceVida.model.Produto;

/**
 * Servlet implementation class EditarProdutoServlet
 */
@WebServlet("/EditarProdutoServlet")
public class EditarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Produto produto;
    private DAOGenerico<Produto> produtoDAO = new ProdutoDAO();
    HttpSession session;   
  
    public EditarProdutoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);
		String id = request.getParameter("id");
		produto = produtoDAO.findById(id);
		session.setAttribute("produto", produto);
		session.setAttribute("observacao", produto.getObservacao());
		
			
		request.getRequestDispatcher("editar-produto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);
		Produto produto = (Produto) session.getAttribute("produto");
		
		request.setCharacterEncoding("UTF-8");
	
		String nmproduto=request.getParameter("nmproduto");
		String tam = request.getParameter("tam");
		String quant = request.getParameter("quant");
		String vlunit = request.getParameter("vlunit");
		String obs =request.getParameter("obs");
		Produto produtoAlterado = new Produto();
		System.out.println("ID "+produto.getId());
		
		produtoAlterado.setId(produto.getId());
		produtoAlterado.setNmProduto(nmproduto);
		produtoAlterado.setTamanho(tam);
		produtoAlterado.setObservacao(obs);
		if(quant.equals("") || quant.equals(null)){
			quant = "0";
			
		}
		produtoAlterado.setQuantidadeEstoque(Integer.parseInt((quant)));
		
		if(vlunit.equals("")|| vlunit.equals(null)){
			vlunit = "0";
		}
		vlunit = vlunit.replace(",", ".");
		BigDecimal valorUnitario = new BigDecimal(vlunit);
		produtoAlterado.setValorUnitario(valorUnitario);
		DAOGenerico<Produto> produtoDAO = new ProdutoDAO();
		produtoDAO.editar(produtoAlterado);
		request.getRequestDispatcher("home.jsp").forward(request, response);
		
	}

}
