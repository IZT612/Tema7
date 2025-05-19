package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import utiles.Constantes;

public class Ejercicio10 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int curso;
		
		try (Connection con = conexion()) {
			
		
		
		System.out.println("Introduzca el curso del que quiere ver los estudiantes:");
		curso = sc.nextInt();
		sc.nextLine();
		
		String sql = "SELECT nombre, fecha_nacimiento FROM estudiantes WHERE id_estudiante IN (SELECT id_estudiante FROM matriculas WHERE id_curso = " + curso + ");";
		
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			
			System.out.println("Nombre: " + rs.getString("nombre") + "\t|\tFecha de nacimiento: " + rs.getString("fecha_nacimiento"));
			
		}
		
		} catch (SQLException e) {
			
			System.out.println(e);
			
		}
		
		sc.close();
		
	}

	static private Connection conexion() {

		Connection con = null;

		try {

			con = DriverManager.getConnection(Constantes.url, Constantes.usuario, Constantes.contraseña);

		} catch (SQLException e) {
			System.out.println("ERROR con la conexión");
		}

		return con;

	}
}
