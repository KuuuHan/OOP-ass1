package Module;
/**
 * This class represents a Bank in the Claim Management System.
 * It contains all the details of a bank, including the bank name, account holder name, and account number.
 * @author Han Duc Khang - s3986602
 */

import java.util.Date;
import java.util.List;

public class Claim {
    private Customer customer;
    private String claimID;
    private Date claimDate;
    private Date examDate;
    private double claimAmount;
    private ClaimStatus claimStatus;
    private String insuredPerson;
    private String insuranceCardID;
    private Bank receiverBank;
    private List<String> documents;


    public Claim(String claimID, Date claimDate, Date examDate, double claimAmount,
                 ClaimStatus claimStatus, String insuredPersonID, String insuranceCardID,
                 Bank receiverBank, List<String> documents)
    {
        this.claimID = claimID;                     // ID of the claim (format as f-123456789)
        this.claimDate = claimDate;                 // Date of the claim created
        this.examDate = examDate;                   // Date claim examined
        this.claimAmount = claimAmount;             // Amount of the claim (money)
        this.claimStatus = claimStatus;             // Status of the claim
        this.insuredPerson = insuredPersonID;         // the id of the person who is insured (format as c-1234567)
        this.insuranceCardID = insuranceCardID;       // the insurance card of the person (card number, cardholder, policy owner)
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

    public String getInsuredPerson() {
        return insuredPerson;
    }

    public String getInsuranceCardID() {
        return insuranceCardID;
    }

    public Bank getReceiverBank() {
        return receiverBank;
    }

    public List<String> getDocuments() {
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


    public String getInsuredPersonID() {
        return this.customer.getId();
    }
}
