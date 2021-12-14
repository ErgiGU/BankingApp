package pleasefivebank.Objects;

public class StudentAccount extends Account{
    private final String university;
    private final String accountNr;
    private final String accountIBAN;

    public StudentAccount(long balance, boolean frozen, String accountNr, String accountIBAN, String university) {
        super(balance, frozen);
        this.accountNr = accountNr;
        this.accountIBAN = accountIBAN;
        this.university = university;
    }
}
