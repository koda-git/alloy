package org.mcmasterkboys.codenamehenryford.objects.posts;

import lombok.Getter;
import me.hy.libhyextended.objects.DatabaseObject;
import org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory;

@Getter
public class Post extends DatabaseObject {

    private String uuid;
    private String title;
    private String content;
    private String authorUUID;
    private String authorName;
    private String boardUUID;
    private long postTime;
    private int likes;
    private int dislikes;

    private String tableName = "posts";
    private String pkName = "uuid";
    private String pkType = "String";

    public Post() {
        super(SQLConnectionFactory.class);
    }

    public Post(String uuid, String title, String content, String authorUUID, String authorName, String boardUUID, long postTime, int likes, int dislikes) {
        super(SQLConnectionFactory.class);
        this.uuid = uuid;
        this.title = title;
        this.content = content;
        this.authorUUID = authorUUID;
        this.authorName = authorName;
        this.boardUUID = boardUUID;
        this.postTime = postTime;
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
