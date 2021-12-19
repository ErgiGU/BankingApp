package pleasefivebank.Objects;

public class StudentAccount extends Account{
    private final String university = "";
    private int rewardPoints;
    private Loan loan;

    public StudentAccount(String accountNr, String accountIBAN, long balance, boolean frozen){
        super(accountNr, accountIBAN, balance, frozen);
    }

    public void requestLoan(){
    }
}
