package server;

import java.sql.*;

public class WorkWithSql {

    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement ps;
    private static ResultSet rs;


    public boolean addRegistration(String login, String password, String nickname) {
        connect();
        try {
            ps = connection.prepareStatement("SELECT * FROM registration WHERE login = ?;");
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                rs.close();
                disconnect();
                return false;
            }
            ps = connection.prepareStatement("SELECT * FROM registration WHERE nickname = ?;");
            ps.setString(1, nickname);
            rs = ps.executeQuery();
            if (rs.next()) {
                rs.close();
                disconnect();
                return false;
            }
            ps = connection.prepareStatement("INSERT INTO registration (login, password, nickname) " +
                    "VALUES (?, ?, ?);");
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, nickname);
            ps.executeUpdate();
            disconnect();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkRegistration(String login, String password) {
        try {
            connect();
            ps = connection.prepareStatement("SELECT * FROM registration WHERE login = ? AND password = ?;");
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                rs.close();
                disconnect();
                return true;
            } else {
                rs.close();
                disconnect();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String returnNickname(String login, String password) {
        try {
            connect();
            ps = connection.prepareStatement("SELECT * FROM registration WHERE login = ? AND password = ?;");
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = connection.prepareStatement("SELECT nickname FROM registration WHERE login = ?;");
                ps.setString(1, login);
                rs = ps.executeQuery();
                String nickname = rs.getString("nickname");
                rs.close();
                disconnect();
                return nickname;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateNickname(String oldNickname, String newNickname) {
        try {
            connect();
            ps = connection.prepareStatement("SELECT * FROM registration WHERE nickname = ?;");
            ps.setString(1, newNickname);
            rs = ps.executeQuery();
            if (rs.next()) {
                rs.close();
                disconnect();
                return false;
            } else {
                ps = connection.prepareStatement("UPDATE registration SET nickname = ? WHERE nickname = ?;");
                ps.setString(1, newNickname);
                ps.setString(2, oldNickname);
                ps.executeUpdate();
            }
            disconnect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearDb() {
        try {
            connect();
            stmt.executeUpdate("DELETE FROM registration;");
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


