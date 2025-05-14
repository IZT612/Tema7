package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utiles.Constantes;

public class Ejercicio4 {
	
	
	public static void main(String[] args) {
		
		try (Connection con = conexion()) {
			
			int[] res;
			
			System.out.println("Comprobamos todos los cursos:\n");
			
			System.out.println(selectAllCursos());
			
			System.out.println("\nAhora introducimos todos y volvemos acomprobar:\n");
			
			String sql = "INSERT INTO cursos (nombre, descripcion, año_escolar) VALUES (?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			
			
			st.setString(1, "Historia 1º");
			st.setString(2, "Primer año de historia.");
			st.setInt(3, 2025);
			
			st.addBatch();
			
			st.setString(1, "Biología 1º");
			st.setString(2, "Primer año de biología.");
			st.setInt(3, 2025);
			
			st.setString(1, "Educación Física 1º");
			st.setString(2, "Primer año de educación física.");
			st.setInt(3, 2025);
			
			st.addBatch();
			
			st.setString(1, "Música 1º");
			st.setString(2, "Primer año de música.");
			st.setInt(3, 2025);
			
			st.addBatch();
			
			st.setString(1, "Tecnología 1º");
			st.setString(2, "Primer año de tecnología.");
			st.setInt(3, 2025);
			
			st.addBatch();
			
			res = st.executeBatch();
			
			System.out.println(selectAllCursos());
			
			
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
	
	static private String selectAllCursos() {
		
		String resultado = "";
		
		try (Connection con = conexion()) {
			
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM cursos");
			
			while (rs.next()) {
				
				resultado += rs.getInt("id_curso") + " | " + rs.getString("nombre") + " | " + rs.getString("descripcion") + " | " + rs.getInt("año_escolar") + "\n";
				
			}
			
		} catch (SQLException e) {

			System.out.println(e);

		}
		
		return resultado;
		
	}

}
