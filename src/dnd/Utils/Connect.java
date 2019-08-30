package dnd.Utils;

import java.sql.*;

public class Connect {
    private static Connection connection = null;
    private static final String url = "jdbc:sqlite:dnddb.db";

    //TODO: подготовить переход на PostgreSQL
    //private static final String url = "jdbc:postgresql://127.0.0.1:5432/postgres?user=postgres&password=12";

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
    public PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
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

    //Закрытие соединения
    public void closeConnection() {
        if (connection != null) {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}