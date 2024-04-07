package Module;
/**
 * @Author Han Duc Khang - s3986602
 */

import java.util.Date;
import java.util.Set;

public class Claim {
    private Customer customer;
    private String claimID;
    private Date claimDate;
    private Date examDate;
    private double claimAmount;
    private ClaimStatus claimStatus;
    private Customer insuredPerson;
    private InsuranceCard insurancecard;
    private Bank receiverBank;
    private Set<String> documents;


    public Claim(String claimID, Date claimDate, Date examDate, double claimAmount,
                 ClaimStatus claimStatus, Customer insuredPerson, InsuranceCard insurancecard,
                 Bank receiverBank, Set<String> documents)
    {
        this.claimID = claimID;                     // ID of the claim (format as f-123456789)
        this.claimDate = claimDate;                 // Date of the claim created
        this.examDate = examDate;                   // Date claim examined
        this.claimAmount = claimAmount;             // Amount of the claim (money)
        this.claimStatus = claimStatus;             // Status of the claim
        this.insuredPerson = insuredPerson;         // the id of the person who is insured (format as c-1234567)
        this.insurancecard = insurancecard;        // the insurance card of the person (card number, cardholder, policy owner)
        this.receiverBank = receiverBank;           // bank information of the claimer (bank name, account holder name, account number)
        this.documents = documents;                // list of proving documents of the claim (e.g. medical bills, doctor's note, etc. formatted as abc.pdf - handle as a String)
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

    public ClaimStatus getClaimStatus() {
        return claimStatus;
    }

    public Customer getInsuredPerson() {
        return insuredPerson;
    }

    public InsuranceCard getInsurancecard() {
        return insurancecard;
    }

    public Bank getReceiverBank() {
        return receiverBank;
    }

    public Set<String> getDocuments() {
        return this.documents;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public void setStatus(String status) {
        this.claimStatus = ClaimStatus.valueOf(status);
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
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
