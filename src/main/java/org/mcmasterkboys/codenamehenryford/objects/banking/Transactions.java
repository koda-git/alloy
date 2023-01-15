package org.mcmasterkboys.codenamehenryford.objects.banking;

import lombok.Getter;
import lombok.Setter;
import me.hy.libhycore.CoreSHA;
import me.hy.libhyextended.objects.DatabaseObject;
import org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Getter
@Setter
public class Transactions extends DatabaseObject {

    private String hashRecord; // PK, Hashed: time, ownerUUID, senderUUID, receiverUUID, amount, currency
    private String ownerUUID;
    private String ownerAccount;
    private String senderAccount;
    private String receiverAccount;
    private float amount;
    private String currency;
    private String transactionName;
    private long transactionTime;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;


    private String tableName = "transactions";
    private String pkName = "hashRecord";
    private String pkType = "String";

    public Transactions() {
        super(SQLConnectionFactory.class);
        transactionTime = System.currentTimeMillis();
        generateHashRecord();
    }

    public Transactions(String ownerUUID, String ownerAccount, String senderAccount, String receiverAccount, float amount, String currency, String transactionName, long transactionTime) {
        super(SQLConnectionFactory.class);
        this.ownerUUID = ownerUUID;
        this.ownerAccount = ownerAccount;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
        this.currency = currency;
        this.transactionTime = transactionTime;
        this.transactionName = transactionName;
        generateHashRecord();

        Date date = new Date(transactionTime);
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

    public void generateHashRecord() {
        this.hashRecord = CoreSHA.hash256(transactionTime + ownerUUID + senderAccount + receiverAccount + amount + currency).substring(0, 16);
    }

    public String getTimestamp() {
        Date date = new Date(transactionTime);
        DateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        return format.format(date);
    }
}
