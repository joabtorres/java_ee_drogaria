package br.com.drogaria.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static final String URL = "jdbc:mysql://localhost:3306/drogaria";
	private static final String USER = "root";
	private static final String PASSWORD = "96560262";

	public static Connection getConnection() throws SQLException {
		
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
		Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
		return conexao;
	}

}
