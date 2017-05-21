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
		    	 daoUsuario = new Usuario(nome, senha);
		    	 
		    	 if(nomeDigitado.equals(daoUsuario.getNome()) 
			    		 && senhaDigitada.equals(daoUsuario.getSenha())){
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
				"INSERT INTO Usuario (nm_usuario, ds_senha)"
				+ "VALUES (?,?)");
		ps.setString(1, user.getNome());
		ps.setString(2, user.getSenha());
		
		
		ps.executeUpdate();
		ps.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	

	}
}
