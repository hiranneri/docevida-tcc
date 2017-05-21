package br.com.doceVida.testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection con;
	private static ConnectionFactory inst;
	
	public static ConnectionFactory getInstance(){
		if(inst==null){
			return inst = new ConnectionFactory();
		}
		return inst;
	}
	public static Connection getConnection(){
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/DoceVida";
		String user = "root";
		String pass = "0206";
		
		try {
			Class.forName(driverName);
			return con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void fecharConexao(){
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
