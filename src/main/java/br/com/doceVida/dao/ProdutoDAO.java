package br.com.doceVida.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import br.com.doceVida.model.Item;
import br.com.doceVida.model.Produto;
import br.com.doceVida.testes.ConnectionFactory;

public class ProdutoDAO implements DAOGenerico<Produto>{

	private PreparedStatement ps = null;
	
	private ResultSet rs = null;
	
	private Connection con = ConnectionFactory.getInstance().getConnection();

	@Override
	public String inserir(Produto produto) {
		
		try {
			
			ps = con.prepareStatement(
					"INSERT INTO Produtos (nm_produto, dc_tamanho,qt_quantidade, vl_unitario, "
					+ "dc_observacao, st_status) VALUES (?,?,?,?,?,?)");
			ps.setString(1, produto.getNmProduto());
			ps.setString(2, produto.getTamanho());
			ps.setInt(3, produto.getQuantidadeEstoque());
			ps.setBigDecimal(4, produto.getValorUnitario());
			ps.setString(5, produto.getObservacao());
			ps.setBoolean(6, true);
			ps.executeUpdate();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Cadastrado com sucesso";
		
	}

	
	public int editar(Produto produto) {
		// TODO Auto-generated method stub
		try {
			ps = con.prepareStatement(
					"UPDATE Produtos SET nm_produto = ?, dc_tamanho= ?, qt_quantidade=?, "
					+ "vl_unitario=?, dc_observacao=? WHERE id_produto = " + produto.getId());
			ps.setString(1, produto.getNmProduto());
			ps.setString(2, produto.getTamanho());
			ps.setInt(3, produto.getQuantidadeEstoque());
			ps.setBigDecimal(4, produto.getValorUnitario());
			ps.setString(5, produto.getObservacao());
			
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public int excluir(int tipo) {
		try{
			ps = con.prepareStatement("UPDATE Produtos SET st_status= ? "
					+ "WHERE id_produto = ?");
			ps.setBoolean(1, false);
			ps.setInt(2, tipo);
			ps.executeUpdate();
			ps.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public List<Produto> listar(String param, String valor) {
		List<Produto> produtosLocalizados = new ArrayList<>();
		try {
			if(param.equalsIgnoreCase("Nmproduto")){
				param = "nm_produto";
			}
					
			String sql=
					"SELECT id_produto, nm_produto, dc_tamanho, qt_quantidade, vl_unitario, dc_observacao "+
					 " from Produtos where "
					+ param +" like '%"+ valor +"%' and st_status = 1;";
			
			 ps = (PreparedStatement) con.prepareStatement(sql);
			 rs = ps.executeQuery();
			
			while(rs.next()){
				long id = rs.getLong("id_produto");
				String nmproduto = rs.getString("nm_produto");
				String tam = rs.getString("dc_tamanho");
				int quant = rs.getInt("qt_quantidade");
				BigDecimal vlunit = rs.getBigDecimal("vl_unitario");
				String obs = rs.getString("dc_observacao");
				
				Produto novoProduto = new Produto();
				novoProduto.setId(id);
				novoProduto.setNmProduto(nmproduto);
				novoProduto.setTamanho(tam);
				novoProduto.setQuantidadeEstoque(quant);
				novoProduto.setValorUnitario(vlunit);
				novoProduto.setObservacao(obs);
				
				
				produtosLocalizados.add(novoProduto);
			}
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return produtosLocalizados;
	}

	@Override
	public Produto findById(String id) {
		String sql = "SELECT * from Produtos where id_produto = "+id;
		
		Produto produto = new Produto();
		try{
			 ps = (PreparedStatement) con.prepareStatement(sql);
			 rs = ps.executeQuery();
		     while(rs.next()) {
		    	 long idproduto = rs.getLong("id_produto");
		    	 String nmproduto = rs.getString("nm_produto");
		    	 String tam = rs.getString("dc_tamanho");
		    	 int quant = rs.getInt("qt_quantidade");
		    	 BigDecimal vlunit = rs.getBigDecimal("vl_unitario");
		    	 String obs = rs.getString("dc_observacao");
		    	 
		    	 produto.setId(idproduto);
		    	 produto.setNmProduto(nmproduto);
		    	 produto.setTamanho(tam);
		    	 produto.setQuantidadeEstoque(quant);
		    	 produto.setValorUnitario(vlunit);
		    	 
		    	 produto.setObservacao(obs);
		    	 
		     }
		    
		     rs.close();
		     ps.close();
		   
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return produto;
	}

	public List<Produto> findByName(String valor) {

		
		String sql = "SELECT id_produto, nm_produto, dc_tamanho,qt_quantidade,vl_unitario from Produtos "
				+ "where nm_produto = '"+valor+"' and st_status=true";
		System.out.println(sql);
		Produto produto = new Produto();
		List<Produto> produtos = new ArrayList<>();
		try{
			
			 ps = (PreparedStatement) con.prepareStatement(sql);
			 rs = ps.executeQuery();
		     while(rs.next()) {
		    	 long idProduto = rs.getLong("id_produto");
		    	 String nome = rs.getString("nm_produto");
		    	 String tamanho = rs.getString("dc_tamanho");
		    	 int quant = rs.getInt("qt_quantidade");
		    	 BigDecimal vlunit = rs.getBigDecimal("vl_unitario");
		    	 
		    	 produto.setId(idProduto);
		    	 produto.setNmProduto(nome);
		    	 produto.setTamanho(tamanho);
		    	 produto.setQuantidadeEstoque(quant);
		    	 produto.setValorUnitario(vlunit);
		    	
		    	 produtos.add(produto);
		     }
		    
		     rs.close();
		     ps.close();
		   
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return produtos;
	}


	



	
}


