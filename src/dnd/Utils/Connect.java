package dnd.Utils;

import java.sql.*;

public class Connect {
    private static Connection connection = null;
    private static final String url = "jdbc:sqlite:dnddb.db";

    //приватный класс, обеспечивающий установление подключения
    private static Connection getConnection() {
        try {
            // создаем соединение к базе данных
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //Закрытие соединения
    public static void CloseConnection() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Получение экземпляра PrepareStatement и SELECT
    public PreparedStatement getSelectPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
    }

    //Получение экземпляра PrepareStatement и UPDATE
    public Statement getUpdatePrepareStatement(String sql) throws SQLException, ClassNotFoundException {
        Statement s = null;
        try {
            s = getConnection().createStatement();
            s.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                s.close();
            }
        } CloseConnection();
        return s;
    }

    //Закрытие PrepareStatement
    public void closePrepareStatement(PreparedStatement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}