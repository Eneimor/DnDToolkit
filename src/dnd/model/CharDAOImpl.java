package dnd.model;

import dnd.Utils.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CharDAOImpl{
    public static final String SELECT_ALL_CHARACTERS = "SELECT a.id as id, a.name as name, b.name as class FROM m_character as a " +
            "left join cl_class as b ON a.[class] = b.id";

    //Обсервабл лист для отображения в TableView списка персонажей
    private static ObservableList<Character> characterData = FXCollections.observableArrayList();

    //Наполняем наблюдаемй список результатом запроса
    public static List<Character> getAll() {
        Connect connect = new Connect();
        List<Character> lst = new LinkedList<>();
        PreparedStatement ps = connect.getPrepareStatement(SELECT_ALL_CHARACTERS);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Character ch = new Character();
                ch.setId(rs.getInt("id"));
                ch.setName(rs.getString("name"));
                ch.setChClass(rs.getString("class"));
                characterData.add(ch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connect.closePrepareStatement(ps);

        }
        return lst;
    }

    //метод, возвращающий список персонажей в виде наблюдаемого списка
    public static ObservableList<Character> getCharacterData() {
        return characterData;
    }



    public void insert(Character character) {
        //TODO сделать метод INSERT
    }

    public static void delete(int id) {
        String sql = "DELETE FROM m_character WHERE id =" + id + ";";
        Connect connect = new Connect();
        PreparedStatement ps = connect.getPrepareStatement(sql);
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Character character) {}
}
