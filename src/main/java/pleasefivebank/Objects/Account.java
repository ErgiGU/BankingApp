package pleasefivebank.Objects;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public abstract class Account {

    protected long balance;
    protected int rewardPoints;
    protected boolean frozen;
    protected final String accountNr;
    protected final String accountIBAN;
    protected ArrayList<Transaction> activity = new ArrayList<>();
    protected ArrayList<Transaction> pending = new ArrayList<>();

    public Account(String accountNr, String accountIBAN, long balance, boolean frozen) {
        this.accountNr = accountNr;
        this.accountIBAN = accountIBAN;
        this.balance = balance;
        this.frozen = false;
        this.rewardPoints = 0;
    }

    public long getBalance() {
        return balance;
    }

    public boolean getFrozen() {
        return frozen;
    }

    /*public int getRewardPoints() { return rewardPoints; }*/

    public String getAccountNr() {
        return accountNr;
    }

    public String getAccountIBAN() {
        return accountIBAN;
    }

    public ArrayList<Transaction> getSent() {
        return activity;
    }

    public ArrayList<Transaction> getReceived() {
        return pending;
    }


    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public void setSent(ArrayList<Transaction> sent) {
        this.activity = activity;
    }

    public void setReceived(ArrayList<Transaction> received) {
        this.pending = pending;
    }

    public void addPoint(int pointsToAdd) {
        this.rewardPoints += pointsToAdd;
    }

    public void freezeAccount() {
        this.frozen = true;
    }

    public void unfreezeAccount() {
        this.frozen = false;
    }

    public void showCard() {
        //get card info and show it
    }
}


