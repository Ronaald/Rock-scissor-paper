package sample;

import java.sql.*;

public class Databas {


    private Connection connection = null;

    public void startaConnection() {

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Game", "postgres", "Fredag14");

            connection.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public int login(String username, String password) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT \"UserID\" FROM \"Users\"" +
                    " where \"UserName\" = ? and \"Password\" = ?; ");
            stmt.setString(1, username);
            stmt.setString(2, password);


            ResultSet rs = stmt.executeQuery();
            int UserID = -1;
            if (rs.next()) {
                UserID = rs.getInt(1);
            }
            return UserID;
        } catch (Exception e) {
            System.out.println(e.getMessage());


        }
        if (stmt != null) {
            stmt.close();
        }
        return 0;
    }

     public int getFriend(long user_id) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM \"Contacts\"" +
                    "WHERE \"UserID\" = ?; ");
            stmt.setLong(1, user_id);


            ResultSet rs = stmt.executeQuery();
            // ResultSet validUser = stmt.executeQuery();
            int antal = -1;
            if (rs.next()) {
                antal = rs.getInt("0");
            }
            return antal;
        } catch (Exception e) {
            System.out.println(e.getMessage());


        }
        if (stmt != null) {
            stmt.close();
        }
         return 0;
     }


}