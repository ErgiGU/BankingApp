package pleasefivebank.Objects;

public abstract class Account {
    private long balance;
    private int rewardPoints;
    private boolean frozen;
    private String accountNr;
    private String accountIBAN;

    //gu student account

    //chalmers student account
    public Account(long balance, boolean frozen) {
        this.balance = balance;
        this.frozen = false;
        this.rewardPoints = 0;
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

    public void makeTransaction() {//this in User class
        //ask for input in gui and make transaction if input is valid
    }

    public void requestTransaction() {//this in User class
        //ask for input in gui and request transaction if input is valid
    }

    public void showCard() {
        //get card info and show it
    }


}

