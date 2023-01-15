package org.mcmasterkboys.codenamehenryford.objects.posts;

import me.hy.libhyextended.objects.DatabaseObject;
import org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory;

public class Comment extends DatabaseObject {

    private String uuid;
    private String content;
    private String authorUUID;
    private String authorName;
    private String postUUID;
    private long postTime;
    private int likes;
    private int dislikes;

    private String tableName = "comments";
    private String pkName = "uuid";
    private String pkType = "String";


    public Comment() {
        super(SQLConnectionFactory.class);
    }
}
