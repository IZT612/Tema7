package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import utiles.Constantes;

public class Ejercicio08 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int estudiante;
		
		try (Connection con = conexion()) {
			
			System.out.print("Introduzca la id del estudiante a eliminar: ");
			estudiante = sc.nextInt();
			sc.nextLine();
			System.out.println("\nAhora veamos todos los estudiantes antes de eliminar:\n");
			System.out.println(selectAllEstudiantes());
			System.out.println("\nAhora eliminaremos al estudiante y volveremos a revisar los estudiantes:\n");
			
			String sql = "DELETE FROM ESTUDIANTES WHERE id_estudiante = " + estudiante;
			
			Statement st = con.createStatement();
		
			st.executeUpdate(sql);
			
			System.out.println(selectAllEstudiantes());
			
		} catch (SQLException e) {
			
			System.out.println(e);
			
		}
		
		sc.close();

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
	
	static private String selectAllEstudiantes() {
		
		String resultado = "";
		
		try (Connection con = conexion()) {
			
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM estudiantes");
			
			while (rs.next()) {
				
				resultado += rs.getInt("id_estudiante") + " | " + rs.getString("nombre") + " | " + rs.getString("apellido") + " | " + rs.getString("fecha_nacimiento") + " | " + rs.getString("email") + " | " + rs.getInt("telefono") + "\n";
				
			}
			
		} catch (SQLException e) {

			System.out.println(e);

		}
		
		return resultado;
		
	}
	
}
