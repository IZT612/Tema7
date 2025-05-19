package ejercicios;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import utiles.Constantes;

public class Ejercicio06 {

	public static void main(String[] args) {
		
		try (Connection con = conexion()) {
			
			System.out.println("Veamos antes de empezar las calificaciones de los alumnos:\n");
			
			System.out.println(selectAllCalificaciones());
			
			System.out.println("\nAhora añadamos 1 a la nota si la estudiante es Ana González y si la asignatura es matemáticas.\n");
			
			// Ana gonzalez es la estudiante 1 y matemáticas es la asignatura 1
			String sql = "UPDATE calificaciones SET nota = (nota + 1) WHERE id_estudiante = 1 AND id_curso = 1";
			
			Statement st = con.createStatement();
			
			st.executeUpdate(sql);
			
			System.out.println("\n" + selectAllCalificaciones());
			
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
