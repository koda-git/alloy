package org.mcmasterkboys.codenamehenryford.objects.posts;

import me.hy.libhyextended.objects.DatabaseObject;

public class Category extends DatabaseObject {

    private String tableName = "posts";
    private String pkName = "uuid";
    private String pkType = "String";

    public Category(Class<?> sqlConnectionFactoryClass) {
        super(sqlConnectionFactoryClass);
    }
}
