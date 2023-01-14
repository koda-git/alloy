package com.maceats.maceats.struct;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Getter
@Setter
public class OrderRecordObject extends JsonifiableObject implements DatabaseObject{

    public static final float PRICE_SERVICE = 5.0f;

    public static class DBStruct {
        public static final String ID = "id";
        public static final String MAC_ID = "macid";
        public static final String RESIDENCE = "res";
        public static final String RESTAURANT = "restaurant";
        public static final String PRICE_TOTAL = "price_total";
        public static final String TIME = "epoch";
        public static final String TIME_ZONE = "timezone";
        public static final String MENU_DATA = "menu_data";
    }

    private int id;
    private String macId;
    private String residence;
    private String restaurant;
    private float priceTotal;
    private long time;
    private int timeZone;
    private String menuData;
    private JsonObject menuDataJson;

    public OrderRecordObject(ResultSet rs) throws SQLException {
        this.id           = rs.getInt(DBStruct.ID);
        this.macId        = rs.getString(DBStruct.MAC_ID);
        this.residence    = rs.getString(DBStruct.RESIDENCE);
        this.restaurant   = rs.getString(DBStruct.RESTAURANT);
        this.priceTotal   = rs.getFloat(DBStruct.PRICE_TOTAL);
        this.time         = rs.getLong(DBStruct.TIME);
        this.timeZone     = rs.getInt(DBStruct.TIME_ZONE);
        this.menuData     = rs.getString(DBStruct.MENU_DATA);
        this.menuDataJson = JsonParser.parseString(this.menuData).getAsJsonObject();
    }

    public OrderRecordObject(String macID, String residence, String restaurantUUID, ArrayList<MenuObject> menus) {
        this.id           = -1;
        this.macId        = macID;
        this.residence    = residence;
        this.restaurant   = restaurantUUID;
        this.time         = System.currentTimeMillis() / 1000L;
        this.timeZone     = -4;
        this.menuDataJson = new JsonObject();

        JsonArray menuArray = new JsonArray();
        for (MenuObject mo : menus) {
            this.priceTotal += mo.getPrice();
            menuArray.add(mo.toJson(mo));
        }

        this.menuDataJson.add("menus", menuArray);
        this.menuData    = this.menuDataJson.toString();
        this.priceTotal += PRICE_SERVICE;
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
