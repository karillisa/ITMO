package bd;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class BdMng {

    public Connection cnt() throws IOException {

        String jdbcUrl = "jdbc:postgresql://localhost:5432/studs";
        String username = "s373432";
        String password = "nKbcajFHZgLw1dhx";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Successfully connected to database");
            return connection;
        } catch (SQLException ex) {
            System.err.println("DataBase Error");
            return null;
        }
    }

    public ResultSet giveResOfQuery(Connection connection, String query){
        try {
            String sql = query;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            return result;
        } catch (SQLException e) {
            System.err.println("Error");
            return null;
        }
    }

    public static void main(String[] args) throws IOException {

    }
}