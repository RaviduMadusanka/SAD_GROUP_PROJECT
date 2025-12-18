/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SMMV.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class connection_ol {

    private static Connection connection;
    private static final String USER = "root";
    private static final String PASSWORD = "Hasinthajava12345!!";
//    Hasinthajava12345!!
//    Ravidu2005Oct13#

    public static Connection getConnection() {

        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/school_project(o/l)", USER, PASSWORD);

            }
            return connection;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ExceptionInInitializerError("Oops! MySQL Connection Failed........!");
        }

    }

    public static void iud(String query) {
        try {
            getConnection().createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }

    public static ResultSet search(String query) throws SQLException {
        return getConnection().createStatement().executeQuery(query);
    }
}
