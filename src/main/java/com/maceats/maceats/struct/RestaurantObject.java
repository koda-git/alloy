package com.maceats.maceats.struct;

import com.maceats.maceats.SQLConnectionAdapter;
import lombok.Getter;
import me.hy.libhyextended.SQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Getter
public class RestaurantObject extends JsonifiableObject implements DatabaseObject{

    public static class DBStruct {
        public static final String TABLE_NAME = "restaurant";
        public static final String NAME = "rname";
        public static final String ADDRESS = "address";
        public static final String PHONE = "phone";
        public static final String UUID = "uuid";
        public static final String ID = "id";
        public static final String OPEN_AT = "openat";
        public static final String CLOSE_AT = "closeat";
    }

    private int id;
    private String name;
    private String address;
    private String phone;
    private String uuid;
    private int openAt;
    private int closeAt;

    public RestaurantObject(ResultSet rs) throws SQLException {
        this.id = rs.getInt(DBStruct.ID);
        this.name = rs.getString(DBStruct.NAME);
        this.address = rs.getString(DBStruct.ADDRESS);
        this.phone = rs.getString(DBStruct.PHONE);
        this.uuid = rs.getString(DBStruct.UUID);
        this.openAt = rs.getInt(DBStruct.OPEN_AT);
        this.closeAt = rs.getInt(DBStruct.CLOSE_AT);
    }

    public ArrayList<MenuObject> getMenus() {

        String sql = "SELECT * FROM " + MenuObject.DBStruct.TABLE_NAME + " WHERE " + MenuObject.DBStruct.RESTAURANT_ID + " = ?;";
        ArrayList<MenuObject> toReturn = new ArrayList<>();

        try {
            SQLConnection c = SQLConnectionAdapter.make();
            ResultSet rs = c.executeQuery(sql, this.uuid);

            while (rs.next()) {
                MenuObject mo = new MenuObject(rs);
                toReturn.add(mo);
            }

            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        return toReturn;
    }

    @Override
    public void save() throws SQLException {

    }

    @Override
    public void update() throws SQLException {

    }

    @Override
    public void delete() throws SQLException {

    }

    @Override
    public void load(String key, String value, String type) throws SQLException {

    }

    @Override
    public void load(int id) throws SQLException {

    }



    public static ArrayList<RestaurantObject> getOpenRestaurant() {

        // Get seconds since midnight. EX: 11:59:59 PM = 86399
        int currentSecondsSinceMidnight = (int) (System.currentTimeMillis() / 1000) % 86400;

        // Create SQL statement
        String sql = "SELECT * FROM " + RestaurantObject.DBStruct.TABLE_NAME + " WHERE " + RestaurantObject.DBStruct.OPEN_AT + " <= ? AND " + RestaurantObject.DBStruct.CLOSE_AT + " >= ?;";

        SQLConnection c = SQLConnectionAdapter.make();

        ArrayList<RestaurantObject> toReturn = new ArrayList<>();

        try {
            ResultSet rs = c.executeQuery(sql, currentSecondsSinceMidnight, currentSecondsSinceMidnight);

            while (rs.next()) {
                RestaurantObject ro = new RestaurantObject(rs);
                toReturn.add(ro);
            }

            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        return toReturn;
    }
}
