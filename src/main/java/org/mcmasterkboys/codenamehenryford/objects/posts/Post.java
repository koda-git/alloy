package org.mcmasterkboys.codenamehenryford.objects.posts;

import lombok.Getter;
import lombok.Setter;
import me.hy.libhyextended.objects.DatabaseObject;
import me.hy.libhyextended.objects.exception.DataFieldMismatchException;
import org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Getter
@Setter
public class Post extends DatabaseObject {

    private String id;
    private String title;
    private String content;
    private String authorUUID;
    private String authorName;
    private String boardUUID;
    private long postTime;
    private int likes;
    private int dislikes;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    private String tableName = "posts";
    private String pkName = "id";
    private String pkType = "String";

    public Post() {
        super(SQLConnectionFactory.class);
    }

    public Post(String id, String title, String content, String authorUUID, String authorName, String boardUUID, long postTime, int likes, int dislikes) {
        super(SQLConnectionFactory.class);
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorUUID = authorUUID;
        this.authorName = authorName;
        this.boardUUID = boardUUID;
        this.postTime = postTime;
        this.likes = likes;
        this.dislikes = dislikes;

        Date date = new Date(postTime);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy/HH/mm/ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);

        String[] dateArray = formatted.split("/");
        this.year = Integer.parseInt(dateArray[2]);
        this.month = Integer.parseInt(dateArray[1]);
        this.day = Integer.parseInt(dateArray[0]);
        this.hour = Integer.parseInt(dateArray[3]);
        this.minute = Integer.parseInt(dateArray[4]);
        this.second = Integer.parseInt(dateArray[5]);
    }

    public Post(String id) throws SQLException, DataFieldMismatchException {
        super(SQLConnectionFactory.class);
        this.id = id;
        this.setPkValue(id);
        this.select();
    }

    public String getRating() {
        float score = (float) likes / (float) (likes + dislikes);
        if (score > 0.75) {
            return "Very helpful";
        } else if (score > 0.5) {
            return "Helpful";
        } else if (score > 0.25) {
            return "Not so helpful";
        } else {
            return "Poor";
        }
    }
}
