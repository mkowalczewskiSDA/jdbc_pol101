package sda.jdbc.przyklad1;

import java.sql.*;

public class Przyklad1 {

    public static void main(String[] args) {

        final String url = "jdbc:mysql://localhost:3306/shop?serverTime=CET";
        final String user = "root";
        final String password = "root";
        final String query = "Select * from Country;";
        final String query2 = "Select * from Address;";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("Country ID: "+resultSet.getInt("CO_ID"));
                System.out.println("Country Name: "+resultSet.getString("CO_NAME"));
                System.out.println("Country Alias: "+resultSet.getString("CO_ALIAS"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
