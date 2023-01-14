package com.maceats.maceats.struct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserObject extends JsonifiableObject implements DatabaseObject{

    public static class DBStruct {
        public static final String TABLE_NAME = "restaurant";
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String EMAIL = "email";
        public static final String PHONENUMBER = "phonenumber";
        public static final String UUID = "uuid";
        public static final String MACID = "macid";
        public static final String PASSWORD = "upassword";
        public static final String VERIFIED = "verified";
        public static final String RESIDENCE_NAME = "resname";
        public static final String RESIDENCE_ROOM = "resroom";
    }

    private int id;
    private String username;
    private String email;
    private String phoneNumber;
    private String uuid;
    private String macId;
    private String password;
    private boolean verified;
    private String residenceName;
    private String residenceRoom;

    public UserObject(ResultSet rs) throws SQLException {
        this.id = rs.getInt(DBStruct.ID);
        this.username = rs.getString(DBStruct.USERNAME);
        this.email = rs.getString(DBStruct.EMAIL);
        this.phoneNumber = rs.getString(DBStruct.PHONENUMBER);
        this.uuid = rs.getString(DBStruct.UUID);
        this.macId = rs.getString(DBStruct.MACID);
        this.password = rs.getString(DBStruct.PASSWORD);
        this.verified = rs.getBoolean(DBStruct.VERIFIED);
        this.residenceName = rs.getString(DBStruct.RESIDENCE_NAME);
        this.residenceRoom = rs.getString(DBStruct.RESIDENCE_ROOM);
    }

    public UserObject(String username, String email, String phoneNumber, String macId, String password, String residenceName, String residenceRoom) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.macId = macId;
        this.password = password;
        this.residenceName = residenceName;
        this.residenceRoom = residenceRoom;
        this.uuid = UUID.randomUUID().toString();
        this.verified = false;
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
