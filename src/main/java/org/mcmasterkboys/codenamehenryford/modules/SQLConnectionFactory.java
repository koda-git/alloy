package org.mcmasterkboys.codenamehenryford.modules;

import me.hy.libhyextended.sql.SQLConnection;

public class SQLConnectionFactory extends me.hy.libhyextended.sql.SQLConnectionFactory {

    @Override
    public SQLConnection getConnection() {
        SQLConnection sqlConnection = new SQLConnection();
        sqlConnection.setUseAutoClose(true);
        sqlConnection.setAddress("192.168.64.5");
        sqlConnection.setUsername("local");
        sqlConnection.setPassword("local");
        sqlConnection.setDatabaseName("henryford");
        return sqlConnection;
    }
}
