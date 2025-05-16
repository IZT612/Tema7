package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utiles.Constantes;

public class Ejercicio5 {

	public static void main(String[] args) {
		
		int[] res;
		
		try (Connection con = conexion()) {
			
			System.out.println("Veamos las notas de los alumnos actuales:\n");
			
			System.out.println(selectAllCalificaciones());
			
			System.out.println("\nAhora añadamos una nota y veamos de nuevo:\n");
			
			String sql = "INSERT INTO calificaciones (id_estudiante, id_curso, id_profesor, tipo_evaluacion, nota, fecha_evaluacion) VALUES (?, ?, ?, ?, ?, ?)"; 
			
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, 1);
			st.setInt(2, 2);
			st.setInt(3, 1);
			st.setString(4, "Examen");
			st.setDouble(5, 9.5);
			st.setString(6, "2025-05-16");
			
			st.addBatch();
			
			st.setInt(1, 2);
			st.setInt(2, 2);
			st.setInt(3, 1);
			st.setString(4, "Examen");
			st.setDouble(5, 6);
			st.setString(6, "2025-05-16");
			
			st.addBatch();
			
			res = st.executeBatch();	
			
			System.out.println(selectAllCalificaciones());
			
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
	
	static private String selectAllCalificaciones() {
		
		String resultado = "Calificacion\tEstudiante\tCurso\t        Profesor\tTipo\t\tNota\t\tFecha";
		
		try (Connection con = conexion()) {
			
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM calificaciones");
			
			while (rs.next()) {
				
				
				resultado += "\n" + rs.getInt("id_calificacion") + "\t\t" + rs.getInt("id_estudiante") + "\t\t" + rs.getInt("id_curso") + "\t\t" + rs.getInt("id_profesor") + "\t\t" + rs.getString("tipo_evaluacion") + "\t\t" + rs.getDouble("nota") + "\t\t" + rs.getString("fecha_evaluacion");
				
			}
			
		} catch (SQLException e) {

			System.out.println(e);

		}
		
		return resultado;
		
	}
	
}
