package jairon.vega.accesdades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	private static final String URL = "jdbc:mysql://localhost:3306/MP06_UF02_AEA1";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	public static void main(String[] args) {
		try {
			//insertarVehiculo("Toyota", "Corolla", 450);
			//leerVehiculos();
			//actualizarVehiculo(1, "Honda", "Civic", 500);
			//leerVehiculos();
			//eliminarVehiculo(0);
			//leerVehiculos();
			
			
			Scanner scanner = new Scanner(System.in);
			/*
			System.out.println("Añade los datos del vehiculo");
			System.out.print("Marca: ");
			String marca = scanner.nextLine();
			System.out.print("Modelo: ");
			String modelo = scanner.nextLine();
			System.out.print("Capacidad Maletero: ");
			int capacidadMaletero = scanner.nextInt();
			insertarVehiculo(marca, modelo, capacidadMaletero);
			
			leerVehiculos();
			*/
			
			/*
			System.out.println("Actualizar Vehiculo:");
			System.out.print("ID: ");
			int id = scanner.nextInt();
			scanner.nextLine(); // consume newline
			System.out.print("Nueva Marca: ");
			String marca = scanner.nextLine();
			System.out.print("Nuevo Modelo: ");
			String modelo = scanner.nextLine();
			System.out.print("Nueva Capacidad Maletero: ");
			int capacidadMaletero = scanner.nextInt();
			actualizarVehiculo(id, marca, modelo, capacidadMaletero);
			
			System.out.println("Leer Vehiculos:");
			leerVehiculos();
			*/
			
			
			System.out.println("Eliminar Vehiculo:");
			System.out.print("ID: ");
			int id = scanner.nextInt();
			eliminarVehiculo(id);

			System.out.println("Leer Vehiculos:");
			leerVehiculos();
			
			
			//scanner.close();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conexión exitosa a la base de datos.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	
	public static void insertarVehiculo(String marca, String modelo, int capacidadMaletero) throws SQLException {
		String sql = "INSERT INTO Vehicles (Marca, Model, CapacitatMaleter) VALUES (?, ?, ?)";
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, marca);
		statement.setString(2, modelo);
		statement.setInt(3, capacidadMaletero);
		statement.executeUpdate();
		System.out.println("Vehiculo insertado correctamente.");
	}

		public static void leerVehiculos() throws SQLException {
		String sql = "SELECT * FROM Vehicles";
		Connection connection = getConnection();
		try (PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				System.out.println("ID: " + resultSet.getInt("Id") + ", Marca: " + resultSet.getString("Marca")
						+ ", Modelo: " + resultSet.getString("Model") + ", Capacidad Maletero: "
						+ resultSet.getInt("CapacitatMaleter"));
			}
			System.out.println("Final de la lectura");
		}
	}

	
	public static void actualizarVehiculo(int id, String marca, String modelo, int capacidadMaletero) throws SQLException {
		String sql = "UPDATE Vehicles SET Marca = ?, Model = ?, CapacitatMaleter = ? WHERE Id = ?";
		Connection connection = getConnection();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, marca);
			statement.setString(2, modelo);
			statement.setInt(3, capacidadMaletero);
			statement.setInt(4, id);
			int filasAfectadas = statement.executeUpdate();
			System.out.println(filasAfectadas > 0 ? "Vehiculo actualizado." : "No se encontró el vehiculo.");
		}
	}

	
	public static void eliminarVehiculo(int id) throws SQLException {
		String sql = "DELETE FROM Vehicles WHERE Id = ?";
		Connection connection = getConnection();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			int filasAfectadas = statement.executeUpdate();
			System.out.println(filasAfectadas > 0 ? "Vehiculo eliminado." : "No se encontró el vehiculo.");
		}
	}
}
