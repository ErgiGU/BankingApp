package pleasefivebank.Objects;

public class BasicAccount extends Account{
    private final String accountNr;
    private final String accountIBAN;
    public BasicAccount(long balance, boolean frozen, String accountNr, String accountIBAN) {
        super(balance, frozen);
        this.accountNr = accountNr;
        this.accountIBAN = accountIBAN;
    }
}
