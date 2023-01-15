package org.mcmasterkboys.codenamehenryford.objects.posts;

import me.hy.libhyextended.objects.DatabaseObject;

public class Comment extends DatabaseObject {

    private String tableName = "comments";
    private String pkName = "uuid";
    private String pkType = "String";



    public Comment(Class<?> sqlConnectionFactoryClass) {
        super(sqlConnectionFactoryClass);
    }
}
