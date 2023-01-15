package org.mcmasterkboys.codenamehenryford.objects.posts;

import me.hy.libhyextended.objects.DatabaseObject;

public class Board extends DatabaseObject {

    private String uuid;
    private String name;
    private String description;

    private String tableName = "board";
    private String pkName = "uuid";
    private String pkType = "String";


    public Board(Class<?> sqlConnectionFactoryClass) {
        super(sqlConnectionFactoryClass);
    }
}
