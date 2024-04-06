package source_code;
/**
 * @Author Han Duc Khang - s3986602
 */

import java.util.Date;
import java.util.List;
public class Claim {
    private String claimID;
    private Date claimDate;
    private Date examDate;
    private double claimAmount;
    private String claimStatus;
    private String insuredPerson;
    private InsuranceCard insurancecard;
    private Bank receiverBank;
    private List<String> documents;
    private ClaimStat status;

    public Claim(String claimID, Date claimDate, Date examDate, double claimAmount,
                 String claimStatus, String insuredPerson, InsuranceCard insurancecard,
                 Bank receiverBank, List<String> documents, ClaimStat status)
    {
        this.claimID = claimID;
        this.claimDate = claimDate;
        this.examDate = examDate;
        this.claimAmount = claimAmount;
        this.claimStatus = claimStatus;
        this.insuredPerson = insuredPerson;
        this.insurancecard = insurancecard;
        this.receiverBank = receiverBank;
        this.documents = documents;
        this.status = status;
    }

    public String getClaimID() {
        return claimID;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public Date getExamDate() {
        return examDate;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public String getInsuredPerson() {
        return insuredPerson;
    }

    public InsuranceCard getInsurancecard() {
        return insurancecard;
    }

    public Bank getReceiverBank() {
        return receiverBank;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public ClaimStat getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return claimID + "," + claimDate + ","
                + claimAmount + "," + documents + ","
                + insuredPerson + "," + receiverBank.getBankName()
                + "," + receiverBank.getAccountHolderName() + ","
                + receiverBank.getAccountNumber() + ","
                + insurancecard.getCardNum() + ","
                + insurancecard.getCardHolder() + ","
                + insurancecard.getPolicyOwner() + ","
                + insurancecard.getCardNum();
    }
}
