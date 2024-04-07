package Module;
/**
 * @Author Han Duc Khang - s3986602
 */
public class Bank {
    private String bankName;
    private String accountHolderName;
    private String accountNumber;           // Account number format: 123456789

    public Bank(String bankName, String accountHolderName, String accountNumber) {
        this.bankName = bankName;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
    }

    // Getters and setters
    public String getBankName() {
        return bankName;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    @Override
    public String toString() {
        return bankName + "," + accountHolderName + "," + accountNumber;
    }
}
