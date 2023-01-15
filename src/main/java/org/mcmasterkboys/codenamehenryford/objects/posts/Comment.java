package org.mcmasterkboys.codenamehenryford.objects.posts;

import lombok.Getter;
import lombok.Setter;
import me.hy.libhyextended.objects.DatabaseObject;
import org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory;

@Getter
@Setter
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

    public Comment(String uuid, String content, String authorUUID, String authorName, String postUUID, long postTime, int likes, int dislikes) {
        super(SQLConnectionFactory.class);
        this.uuid = uuid;
        this.content = content;
        this.authorUUID = authorUUID;
        this.authorName = authorName;
        this.postUUID = postUUID;
        this.postTime = postTime;
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
