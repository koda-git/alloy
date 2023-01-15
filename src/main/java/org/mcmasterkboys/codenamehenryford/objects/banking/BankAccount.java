package org.mcmasterkboys.codenamehenryford.objects.banking;

import lombok.Getter;
import lombok.Setter;
import me.hy.libhyextended.objects.DatabaseObject;
import me.hy.libhyextended.sql.SQLConnection;
import org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

@Getter
@Setter
public class BankAccount extends DatabaseObject {

    private String uuid;
    private String ownerUUID;
    private String accountNumber;
    private String routingNumber;
    private String bankName;
    private String type;
    private String name;
    private float balance;
    private String currency;

    private String tableName = "bank_accounts";
    private String pkName = "uuid";
    private String pkType = "String";
    public BankAccount() {
        super(SQLConnectionFactory.class);
    }

    public BankAccount(String uuid, String ownerUUID, String accountNumber, String routingNumber, String bankName, String type, String name, float balance, String currency) {
        super(SQLConnectionFactory.class);
        this.uuid = uuid;
        this.ownerUUID = ownerUUID;
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.bankName = bankName;
        this.type = type;
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }

    public ArrayList<Transactions> getTransactions(String from, String to) { // YYYY-MM-DD
        SQLConnection sql = new SQLConnectionFactory().getConnection();
        ArrayList<Transactions> transactions = new ArrayList<>();

        String[] fromSplit = from.split("-");
        String[] toSplit = to.split("-");

        try {
            ResultSet rs = sql.executeQuery("SELECT * FROM transactions WHERE ownerAccount = ? AND (year > ? OR (year = ? AND month > ?) OR (year = ? AND month = ? AND day >= ?)) AND (year < ? OR (year = ? AND month < ?) OR (year = ? AND month = ? AND day <= ?))", this.uuid, fromSplit[0], fromSplit[0], fromSplit[1], fromSplit[0], fromSplit[1], fromSplit[2], toSplit[0], toSplit[0], toSplit[1], toSplit[0], toSplit[1], toSplit[2]);
            while (rs.next()) {
                Transactions t = new Transactions();
                t.mapFromResultSet(rs);
                transactions.add(t);
            }
            rs.close();
            sql.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



        return transactions;
    }

    public void generateRandomTransactions() {
        try{
            // Generate 500 random dates between 2022-10-01 and 2023-01-20
            ArrayList<Long> randomDates = new ArrayList<>();
            for (int i = 0; i < 500; i++) {
                long minDay = new SimpleDateFormat("yyyy-MM-dd").parse("2022-10-01").getTime();
                long maxDay = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-20").getTime();
                long randomDay = minDay + (long)(Math.random() * (maxDay - minDay));
                randomDates.add(randomDay);
            }

            // Sort
            Collections.sort(randomDates);

            // Generate 50 random transaction names
            String[] tNames = {"Amazon", "UberEats", "Walmart", "Starbucks", "Target", "Whole Foods", "Best Buy", "Home Depot", "Costco", "Walgreens", "CVS", "Walmart", "Kroger", "Safeway", "Albertsons", "Publix", "Meijer", "Aldi", "Dollar General", "Family Dollar", "Big Lots", "Dollar Tree", "7-Eleven", "BP", "ExxonMobil", "Shell", "Chevron", "ConocoPhillips", "Phillips 66", "Valero", "Marathon Petroleum", "BP", "Chevron", "ExxonMobil", "Shell", "ConocoPhillips", "Phillips 66", "Valero", "Marathon Petroleum", "BP", "Chevron", "ExxonMobil", "Shell"};

            // Generate 500 random transactions
            for (int i = 0; i < 500; i++) {
                Transactions t = new Transactions();
                t.setOwnerAccount(this.accountNumber);
                t.setOwnerUUID(this.ownerUUID);
                t.setSenderAccount(this.accountNumber);
                t.setAmount(-((float) (Math.round(Math.random() * 100000)))/100);
                t.setCurrency("CAD");
                t.setDay(Integer.parseInt(new SimpleDateFormat("dd").format(randomDates.get(i))));
                t.setMonth(Integer.parseInt(new SimpleDateFormat("MM").format(randomDates.get(i))));
                t.setYear(Integer.parseInt(new SimpleDateFormat("yyyy").format(randomDates.get(i))));
                t.setTransactionTime(randomDates.get(i));
                t.setTransactionName(tNames[(int) (Math.random() * tNames.length)]);
                String randomAccountNum = "";
                for (int j = 0; j < 16; j++) {
                    randomAccountNum += (int) (Math.random() * 10);
                }
                t.setReceiverAccount(randomAccountNum);
                t.insert();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
