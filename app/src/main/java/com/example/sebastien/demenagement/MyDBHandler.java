package com.example.sebastien.demenagement;

import java.sql.*;

public class MyDBHandler {
    private Connection cnx;
    private PreparedStatement st;
    private ResultSet rs;

    // Initialisation du driver MySQL
    public void loadDriver() throws ClassNotFoundException{
            Class.forName("com.mysql.jdbc.Driver");
    }

    // Connexion à la base de donnée
    public Connection newConnection() throws SQLException {
        Connection cnx;
        String url = "jdbc:mysql://10.0.2.2/dem-db";
        String user = "root";
        String password = "IMyl8Pt1KwexPp7d";
        cnx = DriverManager.getConnection(url, user, password);
        return cnx;
    }

    // Test du nom d'utilisateur
    public boolean checkUser(String login) {
        try {
            cnx = newConnection();
            st = cnx.prepareStatement("SELECT * FROM users");
            rs = st.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals(login)) {
                    return true;
                }
            }
            rs.close();
            st.close();
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Test de l'email
    public boolean checkMail(String email) {
        try {
            cnx = newConnection();
            st = cnx.prepareStatement("SELECT * FROM users");
            rs = st.executeQuery();
            while (rs.next()) {
                if (rs.getString(10).equals(email)) {
                    return true;
                }
            }
            rs.close();
            st.close();
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Ajout d'un nouvel utilisateur
    public void addUser(String login, String password, int num_ad, String adress, String cplt_ad, int zip, String city, String name,
                        String firstname, String email, Date birth, String sex, int profil) {
        try {
            cnx = newConnection();
            st = cnx.prepareStatement("INSERT INTO users VALUES (?,SHA1(?),?,?,?,?,?,?,?,?,?,?,?)");
            st.setString(1,login);
            st.setString(2,password);
            st.setInt(3,num_ad);
            st.setString(4,adress);
            st.setString(5,cplt_ad);
            st.setInt(6,zip);
            st.setString(7,city);
            st.setString(8,name);
            st.setString(9,firstname);
            st.setString(10,email);
            st.setDate(11,birth);
            st.setString(12,sex);
            st.setInt(13,profil);
            st.executeUpdate();
            cnx.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
