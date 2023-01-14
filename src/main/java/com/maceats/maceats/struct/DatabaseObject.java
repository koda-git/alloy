package com.maceats.maceats.struct;

import java.sql.SQLException;

public interface DatabaseObject {
    public void save() throws SQLException;
    public void update() throws SQLException;
    public void delete() throws SQLException;
    public void load(String key, String value, String type) throws SQLException;
    public void load(int id) throws SQLException;
}
