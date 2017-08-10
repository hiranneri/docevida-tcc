package br.com.doceVida.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import br.com.doceVida.model.Cliente;
import br.com.doceVida.testes.ConnectionFactory;

public class ClienteDAO implements DAOGenerico<Cliente> {
	
	private PreparedStatement ps = null;
	
	private ResultSet rs = null;
	
	
	@Override
	public String inserir(Cliente cliente) {
		String sql = "INSERT INTO Clientes (nm_cliente, dc_cnpj,dc_endereco, nm_casa, dc_bairro,"
				+ "dc_cidade,dc_estado,nm_telefone, nm_celular, dc_email, st_status)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		Connection conexao=null;
		try {
			conexao = ConnectionFactory.getConnection();
			ps = conexao.prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEndereco());
			ps.setInt(4, cliente.getNumero());
			ps.setString(5, cliente.getBairro());
			ps.setString(6, cliente.getCidade());
			ps.setString(7, cliente.getEstado());
			ps.setString(8, cliente.getTelefone());
			ps.setString(9, cliente.getCelular());
			ps.setString(10, cliente.getEmail());
			ps.setBoolean(11, true);
			
			ps.execute();
			ps.close();
			return "Salvo com sucesso";
		} catch (SQLException e) {
			
			throw new RuntimeException(); 
		}
		
	}

	@Override
	public int editar(Cliente cliente) {
		Connection conexao=null;
		try {
			conexao = ConnectionFactory.getConnection();
		
			ps = conexao.prepareStatement(
					"UPDATE Clientes SET nm_cliente = ?, dc_cnpj= ?, dc_endereco=?, nm_casa=?, dc_bairro=?, dc_cidade=?, dc_estado=?, "
					+ " nm_telefone=?,  nm_celular=?, dc_email=? "
					+ " WHERE id_Cliente = " + cliente.getId());
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEndereco());
			ps.setInt(4, cliente.getNumero());
			ps.setString(5, cliente.getBairro());
			ps.setString(6, cliente.getCidade());
			ps.setString(7, cliente.getEstado());
			ps.setString(8, cliente.getTelefone());
			ps.setString(9, cliente.getCelular());
			ps.setString(10, cliente.getEmail());
			
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
			
			ps = conexao.prepareStatement("UPDATE Clientes SET st_status=? where id_Cliente = ?");
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
	public List<Cliente> listar(String param, String valor) {
		List<Cliente> clientesLocalizados = new ArrayList<>();
		try {
			if(param.equalsIgnoreCase("Nome")){
				param = "nm_cliente";
			}else{
				param="dc_endereco";
			} 
			
			
			String sql=
					"SELECT id_Cliente, nm_cliente, dc_endereco, dc_cidade,"
					+ " nm_telefone, nm_celular from Clientes where st_status=true and "
					+ param+" like '%"+valor+"%';";
			Connection conexao=null;
			conexao = ConnectionFactory.getConnection();
			ps  = (PreparedStatement) conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				long id = rs.getLong("id_Cliente");
				String nome = rs.getString("nm_cliente");
				String end = rs.getString("dc_endereco");
				String cid = rs.getString("dc_cidade");
				String tel = rs.getString("nm_telefone");
				String cel = rs.getString("nm_celular");
				
				Cliente novoCliente = new Cliente();
				novoCliente.setId(id);
				novoCliente.setNome(nome);
				novoCliente.setEndereco(end);
				novoCliente.setCidade(cid);
				novoCliente.setTelefone(tel);
				novoCliente.setCelular(cel);
				
				clientesLocalizados.add(novoCliente);
			}
			
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clientesLocalizados;
	}

	@Override
	public Cliente findById(String id) {
		String sql = "SELECT * from Clientes where id_Cliente = "+id+" and st_status=true";
		
		Cliente cliente = new Cliente();
		try{
			Connection conexao=null;
			conexao = ConnectionFactory.getConnection();
			ps = (PreparedStatement) conexao.prepareStatement(sql);
			rs = ps.executeQuery();
		    while(rs.next()) {
		    	 long idCliente = rs.getLong("id_Cliente");
		    	 String nome = rs.getString("nm_cliente");
		    	 String cpf = rs.getString("dc_cnpj");
		    	 String cnpj = rs.getString("dc_cnpj");
		    	 String endereco = rs.getString("dc_endereco");
		    	 int num = rs.getInt("nm_casa");
		    	 String bairro = rs.getString("dc_bairro");
		    	 String cidade = rs.getString("dc_cidade");
		    	 String est = rs.getString("dc_estado");
		    	 String tel = rs.getString("nm_telefone");
		    	 String cel = rs.getString("nm_celular");
		    	 String email = rs.getString("dc_email");
		    	 
		    	 cliente.setId(idCliente);
		    	 cliente.setNome(nome);
		    	 cliente.setCpf(cpf);
		    	 cliente.setCnpj(cnpj);
		    	 cliente.setEndereco(endereco);
		    	 cliente.setNumero(num);
		    	 cliente.setCidade(cidade);
		    	 cliente.setNumero(num);
		    	 cliente.setBairro(bairro);
		    	 cliente.setEstado(est);
		    	 cliente.setTelefone(tel);
		    	 cliente.setCelular(cel);
		    	 cliente.setEmail(email);
		    	 
		     }
		     
		     rs.close();
		     ps.close();
		   
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return cliente;
	}

	public List<Cliente> criarClientes(int i) {
		// TODO Auto-generated method stub
		
		List<Cliente> clientesLocalizados = new ArrayList<>();
		try {
			
			String sql= "select id_Cliente, nm_cliente, dc_endereco "
					+ "from Clientes where st_status = '1' limit = "+i;
			Connection conexao=null;
			conexao = ConnectionFactory.getConnection();
			ps  = (PreparedStatement) conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				long id = rs.getLong("id_Cliente");
				String nome = rs.getString("nm_cliente");
				String end = rs.getString("dc_endereco");
				
				Cliente novoCliente = new Cliente();
				novoCliente.setId(id);
				novoCliente.setNome(nome);
				novoCliente.setEndereco(end);
				
				clientesLocalizados.add(novoCliente);
			}
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clientesLocalizados;
		
	}

	public List<Cliente> pesquisarPeloNome(String nomePesquisado){
		String sql = "SELECT id_Cliente, nm_cliente, dc_endereco from Clientes "
				+ "where nm_cliente = ? and st_status=?";
		
		
		Cliente cliente;
		List<Cliente> clientes = new ArrayList<>();
		try{
			Connection conexao=null;
			conexao = ConnectionFactory.getConnection();
			ps = (PreparedStatement) conexao.prepareStatement(sql);
			ps.setString(1, nomePesquisado);
			ps.setBoolean(2, true);	
			rs = ps.executeQuery();
		     while(rs.next()) {
		    	 long idCliente = rs.getLong("id_Cliente");
		    	 String nome = rs.getString("nm_cliente");
		    	
		    	 String endereco = rs.getString("dc_endereco");
		    	
		    	 cliente = new Cliente();
		    	 cliente.setId(idCliente);
		    	 cliente.setNome(nome);
		    	
		    	 cliente.setEndereco(endereco);
		    	 clientes.add(cliente);
		    	 
		     }
		    
		     rs.close();
		     ps.close();
		   
		}catch(SQLException e){
			throw new RuntimeException();
		}
		
		return clientes;
	}
	
}
