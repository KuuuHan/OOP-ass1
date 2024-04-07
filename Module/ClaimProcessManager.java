package Module;
/**
 * @Author Han Duc Khang - s3986602
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Comparator;

public class ClaimProcessManager implements ClaimProcess{
    private List<Claim> claims;
    private Set<Customer> customers;

    public ClaimProcessManager(Set<Customer> customers, List<Claim> claims) {
        this.claims = new ArrayList<>();
        this.customers = new HashSet<>();
    }

    @Override
    public void add(Claim claim) {
        claims.add(claim);
    }

    @Override
    public void update(Claim claim) { //update the claim.
        for (int i = 0; i < claims.size(); i++) {
            if (claims.get(i).getClaimID().equals(claim.getClaimID())) {
                claims.set(i, claim);
                break;
            }
        }
    }

    @Override
    public void delete(Claim claim) { //delete the claim.
        claims.removeIf(claims -> claim.getClaimID().equals(claim.getClaimID()));
    }

    @Override
    public Claim getOne(String claimID) { //get the claim by user input id.
        for (Claim claim : claims) {
            if (claim.getClaimID().equals(claimID)) {
                return claim;
            }
        }
        return null;
    }

    @Override
    public Set<Claim> getAll() { //get all the claims.
        Set<Claim> claimSet = new HashSet<>(claims);
        List<Claim> claimList = new ArrayList<>(claimSet);
        Collections.sort(claimList, Comparator.comparing(Claim::getClaimID).reversed());
        Set<Claim> sortedClaims = new HashSet<Claim>(claimList);

        return sortedClaims;
    }

    @Override
    public Customer getCustomerById(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public Customer getCustomerId(String id) {
        for (Customer customer : customers)
        {
            if (customer.getId().equals(id))
            {
                return customer;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) { //add the customer.
        customers.add(customer);
    }

    public Claim getClaimById(String claimID) { //get the claim by id.
        for (Claim claim : claims) {
            if (claim.getClaimID().equals(claimID)) {
                return claim;
            }
        }
        return null;
    }
}
