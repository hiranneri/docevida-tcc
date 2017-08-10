package br.com.doceVida.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import br.com.doceVida.model.Produto;
import br.com.doceVida.testes.ConnectionFactory;

public class ProdutoDAO implements DAOGenerico<Produto>{

	private PreparedStatement ps = null;
	
	private ResultSet rs = null;
	
	

	@Override
	public String inserir(Produto produto) {
		
		try {
			Connection conexao=null;
			conexao = ConnectionFactory.getConnection();
			ps = conexao.prepareStatement(
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
		return "Salvo com sucesso";
		
	}

	
	public int editar(Produto produto) {
		try {
			Connection conexao=null;
			conexao = ConnectionFactory.getConnection();
			
			ps = conexao.prepareStatement(
					"UPDATE Produtos SET nm_produto = ?, dc_tamanho= ?, qt_quantidade=?, "
					+ "vl_unitario=?, dc_observacao=? WHERE id_produto = ?");
			ps.setString(1, produto.getNmProduto());
			ps.setString(2, produto.getTamanho());
			ps.setInt(3, produto.getQuantidadeEstoque());
			ps.setBigDecimal(4, produto.getValorUnitario());
			ps.setString(5, produto.getObservacao());
			ps.setLong(6, produto.getId());
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
			Connection conexao=null;
			conexao = ConnectionFactory.getConnection();
			ps = conexao.prepareStatement("UPDATE Produtos SET st_status= ? "
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
			String sql=
					"SELECT id_produto, nm_produto, dc_tamanho, qt_quantidade, vl_unitario,"
					+ " dc_observacao from Produtos where nm_produto like '%?%' and st_status = 1;";
			Connection conexao=null;
			conexao = ConnectionFactory.getConnection();
			ps = conexao.prepareStatement(sql);
			ps.setString(1, valor);
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
		String sql = "SELECT * from Produtos where id_produto = ?";
		Connection con = null;
		Produto produto = new Produto();
		try{
			con = ConnectionFactory.getConnection();
			 ps = con.prepareStatement(sql);
			 ps.setString(1, id);
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

		
		String sql = "SELECT id_produto, nm_produto, dc_tamanho,qt_quantidade,vl_unitario "
				+ "from Produtos where nm_produto = ? and st_status=true";
		
		Produto produto;
		List<Produto> produtos = new ArrayList<>();
		Connection con=null;
		try{
			con=ConnectionFactory.getConnection();
			 ps = (PreparedStatement) con.prepareStatement(sql);
			 ps.setString(1, valor);
			 rs = ps.executeQuery();
		     while(rs.next()) {
		    	 produto = new Produto();
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


