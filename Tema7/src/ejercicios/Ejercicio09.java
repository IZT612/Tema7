package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utiles.Constantes;

public class Ejercicio09 {

	public static void main(String[] args) {
		
		try (Connection con = conexion()) {
			
			String sql = "SELECT nombre, fecha_nacimiento FROM estudiantes;";
			
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				
				System.out.print("Nombre: " + rs.getString("nombre") + "\t|\tFecha de nacimiento: " + rs.getString("fecha_nacimiento"));
				System.out.println();
			}
			
		} catch (SQLException e) {
			
			System.out.println(e);
			
		}

	}
	
	static private Connection conexion() {
		
		Connection con = null;
		
		try  {
			
			con = DriverManager.getConnection(Constantes.url, Constantes.usuario, Constantes.contraseña);
			
		} catch(SQLException e) {
			System.out.println("ERROR con la conexión");
		}
		
		return con;
		
	}

}
