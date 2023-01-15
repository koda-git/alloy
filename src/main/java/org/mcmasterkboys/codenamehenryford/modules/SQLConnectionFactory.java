package org.mcmasterkboys.codenamehenryford.modules;

import me.hy.libhyextended.sql.SQLConnection;

public class SQLConnectionFactory extends me.hy.libhyextended.sql.SQLConnectionFactory {

    @Override
    public SQLConnection getConnection() {
        SQLConnection sqlConnection = new SQLConnection();
        sqlConnection.setUseAutoClose(true);
        sqlConnection.setUsername("local");
        sqlConnection.setPassword("local");
        sqlConnection.setDatabaseName("hford");
        return sqlConnection;
    }
}
