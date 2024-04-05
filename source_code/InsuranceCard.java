package source_code;

public class InsuranceCard {
    private String cardNum;
    private String cardHolder;
    private String policyOwner;
    private String expirationDate;

    public InsuranceCard(String cardNum, String cardHolder, String policyOwner, String expirationDate) {
        this.cardNum = cardNum;
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getPolicyOwner() {
        return policyOwner;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public void setPolicyOwner(String policyOwner) {
        this.policyOwner = policyOwner;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}