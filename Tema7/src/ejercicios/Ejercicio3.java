package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio3 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost/institutodb";
		String usuario = "root";
		String contraseña = "Esteeste1200669!";

		try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {

			System.out.println("Vemos los estudiantes actuales y sus datos:\n");
				
			Statement st = con.createStatement();
			String sql = "SELECT * FROM estudiantes;";
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				
				System.out.println(rs.getInt("id_estudiante") + " | " + rs.getString("nombre") + " | " + rs.getString("apellido") + " | " + rs.getString("fecha_nacimiento") + " | " + rs.getString("email") + " | " + rs.getInt("telefono"));
				
			}
			
			rs.close();
			
			System.out.println("\nAhora eliminamos un registro y volvemos a ver los datos:\n");
			
			Statement st2 = con.createStatement();
			String sql2 = "DELETE FROM estudiantes WHERE id_estudiante = 666;";
			int rs2 = st2.executeUpdate(sql2);
			
			Statement st3 = con.createStatement();
			ResultSet rs3 = st3.executeQuery(sql);
			
			while (rs3.next()) {
				
				System.out.println(rs3.getInt("id_estudiante") + " | " + rs3.getString("nombre") + " | " + rs3.getString("apellido") + " | " + rs3.getString("fecha_nacimiento") + " | " + rs3.getString("email") + " | " + rs3.getInt("telefono"));
				
			}
			
			rs3.close();
			
		} catch (SQLException e) {

			System.out.println(e);

		}

	}

}
