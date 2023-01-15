package org.mcmasterkboys.codenamehenryford.objects.posts;

import lombok.Getter;
import lombok.Setter;
import me.hy.libhyextended.objects.DatabaseObject;
import org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory;

@Getter
@Setter
public class Category extends DatabaseObject {

    private String uuid;
    private String name;
    private String description;
    private String iconPath;

    private String tableName = "category";
    private String pkName = "uuid";
    private String pkType = "String";

    public Category() {
        super(SQLConnectionFactory.class);
    }

    public Category(String uuid, String name, String description, String iconPath) {
        super(SQLConnectionFactory.class);
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.iconPath = iconPath;
    }

    public Category(String categoryUUID) throws Exception {
        super(SQLConnectionFactory.class);
        this.uuid = categoryUUID;
        super.setPkValue(categoryUUID);
        this.select();
    }
}
