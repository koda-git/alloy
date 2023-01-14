package com.maceats.maceats.struct;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
public class MenuObject extends JsonifiableObject implements DatabaseObject{


    public static class DBStruct {
        public static final String TABLE_NAME = "menus";
        public static final String ID = "id";
        public static final String RESTAURANT_ID = "ruuid";
        public static final String MENU_NAME = "menu";
        public static final String DESCRIPTION = "menudesc";
        public static final String PRICE = "price";
        public static final String IMAGE_URL = "image_url";
        public static final String UUID = "menuuuid";
        public static final String ORDER_COUNTS = "ordercounts";
    }

    private int id;
    private String restaurantId;
    private String menuName;
    private String description;
    private float price;
    private String imageUrl;
    private String uuid;
    @Setter private int orderCounts;


    public MenuObject(ResultSet rs) throws SQLException {
        this.id = rs.getInt(DBStruct.ID);
        this.restaurantId = rs.getString(DBStruct.RESTAURANT_ID);
        this.menuName = rs.getString(DBStruct.MENU_NAME);
        this.description = rs.getString(DBStruct.DESCRIPTION);
        this.price = rs.getFloat(DBStruct.PRICE);
        this.imageUrl = rs.getString(DBStruct.IMAGE_URL);
        this.uuid = rs.getString(DBStruct.UUID);
        this.orderCounts = rs.getInt(DBStruct.ORDER_COUNTS);
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
}
