package source_code;
/**
 * @Author Han Duc Khang - s3986602
 */
import java.util.ArrayList;
import java.util.List;

public class ClaimProcessManager implements ClaimProcess{
    private List<Claim> claims;
    private List<Customer> customers;

    public ClaimProcessManager(List<Claim> claims, List<Customer> customers) {
        this.claims = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public ClaimProcessManager(){
        this.claims = new ArrayList<>();
    }

    @Override
    public void add(Claim claim) {
        if (!claim.getClaimID().matches("f-\\d{10}")){
            throw new IllegalArgumentException("Claim ID must start with f- followed by 10 digits");
        } else {
        claims.add(claim);
        System.out.println("Claim added successfully");
        }
    }

    private int findClaimIndex(String id) { //find the index of the claim.
        for (int i = 0; i < claims.size(); i++) {
            if (claims.get(i).getClaimID().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void update(Claim claim) { //update the claim.
        int index = findClaimIndex(claim.getClaimID());
        if (index != -1) {
            claims.set(index, claim);
            System.out.println("Claim updated successfully");
        } else {
            System.out.println("Claim with ID " + claim.getClaimID() + " not found.");
        }
    }

    @Override
    public void delete(Claim claim) { //delete the claim.
        int index = findClaimIndex(claim.getClaimID());
        if (index != -1) {
            claims.remove(index);
            System.out.println("Claim deleted successfully");
        } else {
            System.out.println("Claim with ID " + claim.getClaimID() + " not found.");
        }
    }

    @Override
    public Claim getOne(String claimID) { //get the claim by user input id.
        int index = findClaimIndex(claimID);
        if (index != -1) {
            return claims.get(index);
        } else {
            System.out.println("Claim with ID " + claimID + " not found.");
            return null;
        }
    }

    @Override
    public List<Claim> getAll() { //get all the claims.
        return new ArrayList<>(claims);
    }

    public void getCustomerID(String id) { //get the customer by id.
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                System.out.println(customer);
            }
        }
    }

}
