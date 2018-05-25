package com.example.sebastien.demenagement;

import java.sql.*;

public class MyDBHandler {
    private Connection cnx;
    private PreparedStatement st;
    private ResultSet rs;

    public void loadDriver() throws ClassNotFoundException{
            Class.forName("com.mysql.jdbc.Driver");
    }

    public Connection newConnection() throws SQLException {
        Connection cnx;
        String url = "jdbc:mysql://10.0.2.2/dem-db";
        String user = "root";
        String password = "IMyl8Pt1KwexPp7d";
        cnx = DriverManager.getConnection(url, user, password);
        return cnx;
    }

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void addUser(String login, String password, int num_ad, String adress, String cplt_ad, int zip, String city, String name,
                        String firstname, Date birth, String sex, int profil) {
        try {
            cnx = newConnection();
            st = cnx.prepareStatement("INSERT INTO users VALUES (?,SHA1(?),?,?,?,?,?,?,?,?,?,?)");
            st.setString(1,login);
            st.setString(2,password);
            st.setInt(3,num_ad);
            st.setString(4,adress);
            st.setString(5,cplt_ad);
            st.setInt(6,zip);
            st.setString(7,city);
            st.setString(8,name);
            st.setString(9,firstname);
            st.setDate(10,birth);
            st.setString(11,sex);
            st.setInt(12,profil);
            st.executeUpdate();
            cnx.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
