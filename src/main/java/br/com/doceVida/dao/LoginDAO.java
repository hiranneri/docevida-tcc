package br.com.doceVida.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import br.com.doceVida.model.Usuario;
import br.com.doceVida.testes.ConnectionFactory;

public class LoginDAO {
	private PreparedStatement ps = null;
	
	private ResultSet rs = null;
	Usuario daoUsuario;
	private Connection con = ConnectionFactory.getInstance().getConnection();

	public boolean validarUsuario (Usuario user) {
		String sql = "SELECT * from Usuario where nm_usuario = '"+user.getNome()+
				"' and ds_senha = '"+user.getSenha()+"'";
		
		 
		try{
			 ps = (PreparedStatement) con.prepareStatement(sql);
			 rs = ps.executeQuery();
			 String nomeDigitado = user.getNome();
			 String senhaDigitada = user.getSenha();
			
		     while(rs.next()) {
		    	 String nome = rs.getString("nm_usuario");
		    	 String senha  = rs.getString("ds_senha");
		    	 boolean status = rs.getBoolean("st_status");
		    	 daoUsuario = new Usuario(nome, senha);
		    	 
		    	 if(nomeDigitado.equals(daoUsuario.getNome()) 
			    		 && senhaDigitada.equals(daoUsuario.getSenha()) && status==true){
			    	 return true;
			     }		    	 
		     }
		     
		     rs.close();
		     ps.close();
		   
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return false;
	}

	public void cadastraUsuario(Usuario user) {
		try{
		// TODO Auto-generated method stub
		ps = con.prepareStatement(
				"INSERT INTO Usuario (nm_usuario, ds_senha,st_status)"
				+ "VALUES (?,?,?)");
		ps.setString(1, user.getNome());
		ps.setString(2, user.getSenha());
		ps.setBoolean(3, true);
		
		ps.executeUpdate();
		this.excluirADMIN();
		ps.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	

	}
	public void excluirADMIN() {
		try{
		// TODO Auto-generated method stub
		ps = con.prepareStatement(
				"UPDATE Usuario set st_status = ? where nm_usuario = ? and ds_senha = ?");
		ps.setBoolean(1, false);
		ps.setString(2, "admin");
		ps.setString(3, "admin");
		
		ps.executeUpdate();
		ps.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	

	}
}
