package com.pluralsight;

import java.sql.*;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length != 2){
                System.out.println("Invalid Username and Password Entry!");
                System.exit(1);
            }

            String username = args[0];
            String password = args[1];

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Northwind",
                    username,
                    password
            );

            String query = """
                    select productid, productname, unitprice, unitsinstock
                    From Products
                    """;

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            System.out.printf("%-5s %-33s %-10s %-10s%n", "Id", "Name", "Price", "Stock");
            System.out.println("-------------------------------------------------------");
            while(result.next()){
                int productId = result.getInt("productid");
                String productName = result.getString("productname");
                Double unitPrice = result.getDouble("unitprice");
                int unitsInStock = result.getInt("unitsinstock");

                System.out.printf("%-5d %-33s %-10.2f %-10d%n",
                        productId, productName, unitPrice, unitsInStock);


            }

            result.close();
            statement.close();
            connection.close();


        }catch (Exception e){
            System.out.println("Error Reading File");
        }
    }
}
