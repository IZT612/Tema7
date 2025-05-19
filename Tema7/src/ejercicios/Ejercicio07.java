package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import utiles.Constantes;

public class Ejercicio07 {

	public static void main(String[] args) {
		
		String nombre;
		String apellido;
		String fechaNacimiento;
		String email;
		int telefono;
		
		Scanner sc = new Scanner(System.in);
		
		try (Connection con = conexion()) {
			
			System.out.println("Veamos los estudiantes que hay.\n");
			System.out.println(selectAllEstudiantes());
			System.out.println("\nAhora añadamos uno, introduzca los datos del estudiante.");
			System.out.print("Nombre: ");
			nombre = sc.nextLine();
			System.out.print("\nApellido: ");
			apellido = sc.nextLine();
			System.out.print("\nFecha de nacimiento (YYYY-MM-DD): ");
			fechaNacimiento = sc.nextLine();
			System.out.print("\nEmail: ");
			email = sc.nextLine();
			System.out.print("\nTelefono: ");
			telefono = sc.nextInt();
			
			String sql = "INSERT INTO estudiantes (nombre, apellido, fecha_nacimiento, email, telefono) VALUES (" + nombre + ", " + apellido + ", " + fechaNacimiento + ", " + email + ", " + telefono + ")";
			
			Statement st = con.createStatement();
			
			st.executeUpdate(sql);
			
			System.out.println("\nVeamos de nuevo los estudiantes:\n");
			System.out.println(selectAllEstudiantes());
			
			
		} catch (SQLException e) {
			
			System.out.println(e);
			
		}
		
		sc.close();

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
