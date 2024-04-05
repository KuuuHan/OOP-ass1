package source_code;

import java.util.List;
public class Claim {
    private String claimID;
    private String claimDate;
    private double claimAmount;
    private String claimStatus;
    private String insuredPerson;
    private InsuranceCard insurancecard;

    public Claim(String claimID, String claimDate, double claimAmount, String claimStatus, String insuredPerson, InsuranceCard insurancecard) {
        this.claimID = claimID;
        this.claimDate = claimDate;
        this.claimAmount = claimAmount;
        this.claimStatus = claimStatus;
        this.insuredPerson = insuredPerson;
        this.insurancecard = insurancecard;
    }

    public void setClaimID(String claimID) {
        this.claimID = claimID;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public void setInsuredPerson(String insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    // Link with the insurancecard
    public InsuranceCard getInsurancecard() {
        return insurancecard;
    }

    public void setInsuranceCard(InsuranceCard insurancecard) {
        this.insurancecard = insurancecard;
    }
    public String getCardNumber() {
        return insurancecard.getCardNum();
    }
}
