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
    protected ArrayList<Transaction> sent = new ArrayList<>();
    protected ArrayList<Transaction> received = new ArrayList<>();

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
        return sent;
    }

    public ArrayList<Transaction> getReceived() {
        return received;
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
        this.sent = sent;
    }

    public void setReceived(ArrayList<Transaction> received) {
        this.received = received;
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


