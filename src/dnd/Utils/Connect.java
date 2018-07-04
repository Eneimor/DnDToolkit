package dnd.Utils;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class Connect {

    private String url;

    //приватный класс, обеспечивающий установление подключения
    private Connection getConnection() {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:dnddb.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Получение экземпляра PrepareStatement
    public PreparedStatement getPrepareStatement(String sql) {
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

    //TODO Закрытие соединения
}