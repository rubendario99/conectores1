/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repasoexamenconectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ruchi
 */
public class Ejercicios {

    private Connection conexion;

    public void abrirConexion(String bd, String servidor, String usuario, String password) {
        try {
            String url = String.format("jdbc:mariadb://%s:3306/%s", servidor, bd);
            this.conexion = DriverManager.getConnection(url, usuario, password);// Establecemos la conexión con la BD
            if (this.conexion != null) {
                System.out.println("Conectado a la base de datos " + bd + " en " + servidor);
            } else {
                System.out.println("No se ha conectado a la base de datos " + bd + " en " + servidor);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getLocalizedMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Código error: " + e.getErrorCode());
        }
    }

    public void cerrarConexion() {
        try {
            this.conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getLocalizedMessage());
        }
    }

    public void ejercicio1(String campo, String valor, boolean isNumerico) throws SQLException {

        String query = "";
        abrirConexion("cuarentena", "localhost", "root", "");

        try {
            if (isNumerico) {
                System.out.println(campo.toLowerCase());
                if (campo.toLowerCase().equals("longitud")|| campo.toLowerCase().equals("volumenutil") || campo.toLowerCase().equals("diametro")) {
                    query = "SELECT * FROM naves WHERE " + campo + " = " + Double.parseDouble(valor); //Consulta a ejecutar
                    System.out.println("Is double");
                } else {
                    query = "SELECT * FROM naves WHERE " + campo + " = " + Integer.parseInt(valor); //Consulta a ejecutar
                }
            } else {
                query = "SELECT * FROM naves WHERE " + campo + " = \"" + valor + "\""; //Consulta a ejecutar
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Introduce el tipo de dato correcto");
        }
        Statement statement = this.conexion.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("id"));
            System.out.println("Nombre: " + resultSet.getString("nombre"));
            System.out.println("Pais: " + resultSet.getString("pais"));
            System.out.println("Fabricante: " + resultSet.getString("fabricante"));
            System.out.println("Sistema lanzamiento: " + resultSet.getString("SistemaLanzamiento"));
            System.out.println("Longitud: " + resultSet.getFloat("longitud"));
            System.out.println("Masa en seco: " + resultSet.getInt("MasaSeco"));
            System.out.println("Masa lanzamiento: " + resultSet.getInt("masaLanzamiento"));
            System.out.println("Carga util: " + resultSet.getInt("CargaUtil"));
            System.out.println("Volumen util: " + resultSet.getFloat("volumenUtil"));
            System.out.println("Carga util retorno: " + resultSet.getInt("cargaUtilRetorno"));
            System.out.println("Diametro: " + resultSet.getFloat("Diametro"));
            System.out.println("Potencia: " + resultSet.getInt("Potencia"));
            System.out.println("Estado: " + resultSet.getString("estado"));
        }
        cerrarConexion();
    }
}
