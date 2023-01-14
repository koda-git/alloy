package com.maceats.maceats;

import me.hy.libhyextended.SQLConnection;

public class SQLConnectionAdapter {

    public static SQLConnection make() {
        SQLConnection c = new SQLConnection();
        c.setAddress("localhost");
        c.setDatabaseName("maceats");
        c.setUsername("local");
        c.setPassword("local");
        c.setUseAutoClose(true);

        return c;
    }
}
