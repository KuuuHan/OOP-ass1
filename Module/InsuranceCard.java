package Module;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InsuranceCard {
    private static Map<String, InsuranceCard> cards = new HashMap<>();
    private String cardNum;
    private String cardHolder;
    private String policyOwner;
    Date expirationDate;

    public InsuranceCard(String cardNum, String cardHolder, String policyOwner, Date expirationDate) {
        this.cardNum = cardNum;
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
        cards.put(cardNum, this); // Add the card to the map
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

    public static InsuranceCard getInsuranceCardByCardNumber(String cardNum) {
        return cards.get(cardNum);
    }

}