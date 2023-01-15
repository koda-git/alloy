package org.mcmasterkboys.codenamehenryford.objects.posts;

import me.hy.libhyextended.objects.DatabaseObject;
import org.mcmasterkboys.codenamehenryford.objects.User;

public class Post extends DatabaseObject {

    private String uuid;
    private String title;
    private String content;
    private User author;

    private String tableName = "posts";
    private String pkName = "uuid";
    private String pkType = "String";

    public Post(Class<?> sqlConnectionFactoryClass) {
        super(sqlConnectionFactoryClass);
    }
}
