package com.java.Experiment5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Banking_system {

    public static void Fund_Transfer(int fromAccount, int toAccount, double Amount) {

        String url = "jdbc:mysql://localhost:3306/Banking_system";
        String username = "root";
        String password = "1234";

        try {

            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection c = DriverManager.getConnection(url, username, password);
            c.setAutoCommit(false);

            Statement st = c.createStatement();
            PreparedStatement pst = null;
            ResultSet rs = null;

            // Display account details
            rs = st.executeQuery("SELECT * FROM Account_Details");

            while (rs.next()) {
                System.out.println("Id: " + rs.getInt("Account_id") +
                        ", Name: " + rs.getString("Account_holder_name") +
                        ", Balance: " + rs.getDouble("Balance"));
            }

            // Check balance
            String query = "SELECT Balance FROM Account_Details WHERE Account_id = ?";
            pst = c.prepareStatement(query);
            pst.setInt(1, fromAccount);
            rs = pst.executeQuery();

            if (rs.next()) {

                double Balance = rs.getDouble("Balance");

                if (Balance < Amount) {
                    System.out.println("Insufficient Balance");
                    return;
                }

                // Deduct amount from sender
                String updateFromQuery =
                        "UPDATE Account_Details SET Balance = Balance - ? WHERE Account_id = ?";
                pst = c.prepareStatement(updateFromQuery);
                pst.setDouble(1, Amount);
                pst.setInt(2, fromAccount);
                int rowFrom = pst.executeUpdate();

                // Add amount to receiver
                String updateToQuery =
                        "UPDATE Account_Details SET Balance = Balance + ? WHERE Account_id = ?";
                pst = c.prepareStatement(updateToQuery);
                pst.setDouble(1, Amount);
                pst.setInt(2, toAccount);
                int rowTo = pst.executeUpdate();

                // Transaction commit or rollback
                if (rowFrom > 0 && rowTo > 0) {
                    c.commit();
                    System.out.println("Transfer Successful");
                } else {
                    c.rollback();
                    System.out.println("Transfer Failed");
                }
            }

            // Close resources
            rs.close();
            st.close();
            pst.close();
            c.close();

            System.out.println("Connection closed.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Fund_Transfer(1, 2, 1000);
    }
}
