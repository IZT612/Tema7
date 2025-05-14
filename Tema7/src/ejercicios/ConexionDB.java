package ejercicios;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionDB {
	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost/institutodb";
		
		String usuario = "Ivan";
		
		String contraseña = "Esteeste1200669!";
		
		
		
		
		
		
		
		
		try(Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
			
			System.out.println("Si");
			
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("select * from estudiantes");
			
			while (!rs.next()) {
				
				System.out.print(rs.getString(1) + " " + rs.getString(2));
				
			}
			
		} catch(SQLException e) {
			
			System.out.println("Error con la base de datos: " + e);
			
		}
		
		
		
	}
}
