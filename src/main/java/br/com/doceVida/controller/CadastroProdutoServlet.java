package br.com.doceVida.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.doceVida.dao.DAOGenerico;
import br.com.doceVida.dao.ProdutoDAO;
import br.com.doceVida.model.Item;
import br.com.doceVida.model.Produto;

/**
 * Servlet implementation class CadastroProdutoServlet
 */
@WebServlet("/CadastroProdutoServlet")
public class CadastroProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOGenerico<Produto> inserirProduto = new ProdutoDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroProdutoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Produto novoProduto = new Produto();
		//Nmproduto, quant, tam, obs, vlunit
		String nmproduto, tamanho, observacao;
		int quantidadeEstoque = 0;
		
		nmproduto = request.getParameter("nmproduto");
		tamanho = request.getParameter("tam");
		
		String num = request.getParameter("quant");
		if(num.equals("")|| num.equals(null)){
			num = "0";
		}
		quantidadeEstoque = Integer.parseInt(num);
		
		String strValorUnitario = request.getParameter("vlunit");
		if(strValorUnitario.equals("")|| strValorUnitario.equals(null)){
			strValorUnitario = "0";
		}
		strValorUnitario = strValorUnitario.replace(",", ".");
		BigDecimal valorUnitario = new BigDecimal(strValorUnitario);
		
		observacao = request.getParameter("obs");

		novoProduto.setNmProduto(nmproduto);
		novoProduto.setQuantidadeEstoque(quantidadeEstoque);
		novoProduto.setTamanho(tamanho);
		novoProduto.setValorUnitario(valorUnitario);
		novoProduto.setObservacao(observacao);
	
		
		String resposta = inserirProduto.inserir(novoProduto);
		System.out.println(resposta);
		request.getRequestDispatcher("home.jsp").forward(request, response);
		
		
	}

}
