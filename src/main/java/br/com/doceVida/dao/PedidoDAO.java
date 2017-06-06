package br.com.doceVida.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.doceVida.model.Cliente;
import br.com.doceVida.model.Item;
import br.com.doceVida.model.Pedido;
import br.com.doceVida.testes.ConnectionFactory;

public class PedidoDAO implements DAOGenerico<Pedido> {


	@Override
	public String inserir(Pedido pedido) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Pedido (dt_pedido,dt_entrega,vl_pedido,Clientes_id_Cliente) "+
				"VALUES (?,?,?,?)";
		try {
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			

			java.sql.Date dataPedido = new java.sql.Date(pedido.getDataPedido().getTime());
		    ps.setDate(1, dataPedido);
		    java.sql.Date dataEntrega = new java.sql.Date(pedido.getDataEntrega().getTime());
		    ps.setDate(2, dataEntrega);
			ps.setBigDecimal(3,pedido.getValorTotal());
			ps.setLong(4, pedido.getCliente().getId());
			//Inserir Pedido
			ps.executeUpdate();
			
			//Adicionar Itens do Pedido
			this.inserirItens(pedido);
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Cadastrado com sucesso";
		
	}
	
	private void inserirItens(Pedido pedido){
		long idDoUltimoPedido = this.ultimoPedidoCadastrado().getId();
		int i=0;
		pedido.setId(idDoUltimoPedido);
		
		String sql = "INSERT INTO Item_Pedido (qt_item,pedido_id_pedido,vl_unitario,produtos_id_produto) "
				+ "VALUES (?,?,?,?)";
			try {
				Connection connection = ConnectionFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				
			for(Item item:pedido.getItensDoPedido()){
			    ps.setInt(1, item.getQuantidade());
			    ps.setLong(2, pedido.getId());
			    ps.setBigDecimal(3,item.getProduto().getValorUnitario());
				ps.setLong(4, item.getProduto().getId());
				
				ps.execute();
				
				item.getProduto().diminuirEstoque(item.getQuantidade());
				this.atualizaEstoque(item); //Passar produto com a quantidade atualizada

			}
			
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}
	}
	private Pedido ultimoPedidoCadastrado(){
		Pedido pedido = new Pedido();
		String sql= "select id_pedido,dt_pedido,vl_pedido from Pedido order by id_pedido desc limit 1";
		try{
			
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
		while(rs.next()){
			long idPedido = rs.getLong("id_pedido");
			Date dataPedido = rs.getDate("dt_pedido");
			BigDecimal valorPedido = rs.getBigDecimal("vl_pedido");
			
			
			pedido.setId(idPedido);
			pedido.setDataPedido(dataPedido);
			pedido.setValorTotal(valorPedido);
			
			
				
			}
		rs.close();
		ps.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return pedido;
	}
	public void atualizaEstoque(Item item){
		String sql = "UPDATE Produtos set qt_quantidade = ? where id_produto = ?";
		
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql); 

			ps.setInt(1, item.getProduto().getQuantidadeEstoque());
			ps.setLong(2, item.getProduto().getId());
			
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
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
	
	//Query para a pesquisa de pedido
	/*
	select Clientes.id_Cliente, Clientes.nm_cliente, Pedido.id_pedido, Pedido.dt_pedido, Pedido.vl_pedido
	from Clientes 
	inner join Pedido 
	on Clientes.id_Cliente = Pedido.Clientes_id_Cliente
	where dt_pedido = '' and nm_cliente='';
	 */
	public List<Pedido> pesquisarPorClienteEData(String cliente, Date dataInicio, Date dataFim) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pedido> pesquisarPorCliente(String cliente) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public List<Pedido> pesquisarPorData(Date dataInicio, Date dataFim) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pedido> buscarPorData(Date dataInicio, Date dataFim) {
		// TODO Auto-generated method stub
		List<Pedido> pedidos = new ArrayList<>();
		
		try{	
			
			String sql="select Pedido.id_pedido, Pedido.dt_pedido, Pedido.dt_entrega, "
					+ "Pedido.vl_pedido, Clientes.nm_cliente from Pedido "
					+ "left join Clientes "
					+ "on Clientes.id_cliente = Pedido.Clientes_id_Cliente"
					+ "  where dt_pedido between ? and ?";
			/*select id_pedido from Pedido where dt_pedido between '2017-05-28'and '2017*/
			System.out.println(sql);
			java.sql.Date dataInicioSQL = new java.sql.Date(dataInicio.getTime());
			java.sql.Date dataFimSQL = new java.sql.Date(dataFim.getTime());
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDate(1, dataInicioSQL);
			ps.setDate(2, dataFimSQL);
			ResultSet rs = ps.executeQuery();
			Cliente cliente; 
			while(rs.next()){
				long id = rs.getLong("id_pedido");
				Date dataPedido = rs.getDate("dt_pedido");
				Date dataEntrega = rs.getDate("dt_entrega");
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
			e.printStackTrace();
		}
		
		return pedidos;
		
	}

}
