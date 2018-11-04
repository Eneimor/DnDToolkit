package dnd.model;

import dnd.Utils.Connect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CharDAOImpl {
    public void insert(Character character) {
        //TODO сделать метод INSERT
    }

    public static void delete(int id) {
        String sql = "DELETE FROM m_character WHERE id =" + id + ";";
        Connect connect = new Connect();
        PreparedStatement ps = connect.getPreparedStatement(sql);
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Character character) {}
}
