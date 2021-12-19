package pleasefivebank.Objects;

public class BasicAccount extends Account{

    public BasicAccount(String accountNr, String accountIBAN, long balance, boolean frozen){
        super(accountNr, accountIBAN, balance, frozen);
    }
}
