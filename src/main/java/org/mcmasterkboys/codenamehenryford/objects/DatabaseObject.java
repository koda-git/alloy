package org.mcmasterkboys.codenamehenryford.objects;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.sql.SQLException;

@Getter
public abstract class DatabaseObject extends DataObject {

    @Setter private String pkName;
    @Setter private String pkValue;
    @Setter private String pkType;

    public abstract void insert() throws SQLException;
    public abstract void delete() throws SQLException;
    public abstract void update() throws SQLException;
    public abstract void select() throws SQLException;


}
