package br.com.doceVida.controller.produto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.doceVida.dao.DAOGenerico;
import br.com.doceVida.dao.ProdutoDAO;
import br.com.doceVida.model.Item;
import br.com.doceVida.model.Produto;

@WebServlet("/PesquisarProdutosServlet")
public class PesquisarProdutosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOGenerico<Produto> dadosPesquisa = new ProdutoDAO();   
	List<Produto> produtosLocalizados = new ArrayList<>();  
   
    public PesquisarProdutosServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String parametroPesquisa = "Nmproduto";
		String dadoPesquisa = request.getParameter("valor");
		produtosLocalizados =  dadosPesquisa.listar(parametroPesquisa,dadoPesquisa);
		request.setAttribute("listaProdutos", produtosLocalizados);
		request.setAttribute("resultadoPesquisa", produtosLocalizados.size());
		request.getRequestDispatcher("pesquisar-produtos.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
