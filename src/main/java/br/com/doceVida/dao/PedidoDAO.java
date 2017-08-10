package br.com.doceVida.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.doceVida.model.Cliente;
import br.com.doceVida.model.Item;
import br.com.doceVida.model.Pedido;
import br.com.doceVida.testes.ConnectionFactory;

public class PedidoDAO implements DAOGenerico<Pedido> {

	
	public String inserir(Pedido pedido) {
		String sql = "INSERT INTO Pedido (dt_pedido,dt_entrega,vl_pedido,Clientes_id_Cliente) "+
				"VALUES (?,?,?,?)";
		Connection connection=null;
		try {
			 connection = ConnectionFactory.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		    ps.setDate(1, java.sql.Date.valueOf(pedido.getDataPedido()));
		    ps.setDate(2, java.sql.Date.valueOf(pedido.getDataEntrega()));
			ps.setBigDecimal(3,pedido.getValorTotal());
			ps.setLong(4, pedido.getCliente().getId());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			while(rs.next()){
				pedido.setId(rs.getLong(1));
			}
			
			inserirItens(pedido);
			
			ps.close();
			return "Salvo com sucesso";
			
		} catch (SQLException e) {
			
			if (connection != null) {
	            try {
	                connection.rollback();
	                return "Falha ao efetuar pedido";
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }
	        }
			
	        throw new RuntimeException(e);
	       
	    } finally {
	        if (connection != null) {
	            try { connection.close(); } catch (SQLException e) {} 
	        }
		}
		
	}
	
	private void inserirItens(Pedido pedido){
		String sql = "INSERT INTO Item_Pedido (qt_item,pedido_id_pedido,vl_unitario,"
				+ "produtos_id_produto) VALUES (?,?,?,?)";
		Connection connection = null;
		try{
			connection = ConnectionFactory.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			for(Item item:pedido.getItensDoPedido()){
			    ps.setInt(1, item.getQuantidade());
			    ps.setLong(2, pedido.getId());
			    ps.setBigDecimal(3,item.getProduto().getValorUnitario());
				ps.setLong(4, item.getProduto().getId());
				
				ps.execute();
				
				item.getProduto().diminuirEstoque(item.getQuantidade());
				this.atualizaEstoque(item); 
			}
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}
	}
	private void atualizaEstoque(Item item){
		String sql = "UPDATE Produtos set qt_quantidade = ? where id_produto = ?";
		Connection connection = null;
		try{
			connection = ConnectionFactory.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, item.getProduto().getQuantidadeEstoque());
			ps.setLong(2, item.getProduto().getId());
			
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public int editar(Pedido tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int excluir(int tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Pedido findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Pedido> procurarPeloID (String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Pedido> listar(String param, String valor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	public List<Pedido> buscarPorData(Date dataInicio, Date dataFim) {
		List<Pedido> pedidos = new ArrayList<>();
		Connection connection=null;
		try{	
			
			String sql="select Pedido.id_pedido, Pedido.dt_pedido, Pedido.dt_entrega, "
					+ "Pedido.vl_pedido, Clientes.nm_cliente from Pedido "
					+ "left join Clientes "
					+ "on Clientes.id_cliente = Pedido.Clientes_id_Cliente"
					+ "  where dt_pedido between ? and ?";
			connection = ConnectionFactory.getConnection();
			
			java.sql.Date dataInicioSQL = new java.sql.Date(dataInicio.getTime());
			java.sql.Date dataFimSQL = new java.sql.Date(dataFim.getTime());
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDate(1, dataInicioSQL);
			ps.setDate(2, dataFimSQL);
			ResultSet rs = ps.executeQuery();
			Cliente cliente; 
			while(rs.next()){
				long id = rs.getLong("id_pedido");
				LocalDate dataPedido = rs.getDate("dt_pedido").toLocalDate();
				LocalDate dataEntrega = rs.getDate("dt_entrega").toLocalDate();
				BigDecimal valorPedido = rs.getBigDecimal("vl_pedido");
				String nomeCliente = rs.getString("nm_cliente");
				
				Pedido p = new Pedido();
				
				p.setId(id);
				p.setDataPedido(dataPedido);
				p.setDataEntrega(dataEntrega);
				p.setValorTotal(valorPedido);
				System.out.println(p.getValorPedido());
				cliente = new Cliente();
				cliente.setNome(nomeCliente);
				p.setCliente(cliente);
				
				pedidos.add(p);
			}
			
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			if (connection != null) {
	            try {
	                connection.rollback();
	               
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }
	        }
			
	        throw new RuntimeException(e);
	       
	    } finally {
	        if (connection != null) {
	            try { connection.close(); } catch (SQLException e) {} 
	        }
		}
		
		
		return pedidos;
		
	}

}
