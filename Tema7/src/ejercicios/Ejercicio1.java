package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio1 {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost/institutodb";
		String usuario = "root";
		String contraseña = "Esteeste1200669!";

		try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
			


			Statement st = con.createStatement();
			
			System.out.println("Mostremos todos los estudiantes actuales:\n");
			
			ResultSet rs = st.executeQuery("SELECT * FROM estudiantes;");
			
			while (rs.next()) {
				
				System.out.println(rs.getInt("id_estudiante") + " | " + rs.getString("nombre") + " | " + rs.getString("apellido") + " | " + rs.getString("fecha_nacimiento") + " | " + rs.getString("email") + " | " + rs.getInt("telefono"));
				
			}
			
			System.out.println("\nAhora insertamos uno y volvemos a mostrar todos:\n");
			
			
			Statement st2 = con.createStatement();
			
			String insercion = "INSERT INTO estudiantes VALUES (666, 'Ivan', 'Zamora', '2006-12-06', 'ivanzamora@iesnervion.es', 643419384);";
			
			int rs2 = st2.executeUpdate(insercion);
			
			ResultSet rs3 = st2.executeQuery("SELECT * FROM estudiantes;");

			while (rs3.next()) {
				
				System.out.println(rs3.getInt("id_estudiante") + " | " + rs3.getString("nombre") + " | " + rs3.getString("apellido") + " | " + rs3.getString("fecha_nacimiento") + " | " + rs3.getString("email") + " | " + rs3.getInt("telefono"));
				
			}

			
		} catch (SQLException e) {

			System.out.println(e);

		}

	}

}
