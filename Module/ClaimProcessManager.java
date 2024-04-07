package Module;
/**
 *   This class is responsible for managing the list of claims and customers in the Claim Management System.
 *   It provides methods to add, update, delete, and view claims and customers.
 * @Author Han Duc Khang - s3986602
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Comparator;

public class ClaimProcessManager implements ClaimProcess{
    private List<Claim> claimList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();

    public ClaimProcessManager(List<Customer> customers, List<Claim> claims) {
        this.claimList = claims;
        this.customerList = customers;
    }
    public List<Customer> getCustomers() {
        return customerList;
    }
    public List<Claim> getClaims() {
        return claimList;
    }

    @Override
    public void add(Claim claim) {
        claimList.add(claim);
    }

    @Override
    public void update(Claim claim) { //update the claim.
        for (int i = 0; i < claimList.size(); i++) {
            if (claimList.get(i).getClaimID().equals(claim.getClaimID())) {
                claimList.set(i, claim);
                break;
            }
        }
    }

    @Override
    public void delete(Claim claim) { //delete the claim.
        claimList.removeIf(claims -> claim.getClaimID().equals(claim.getClaimID()));
    }

    @Override
    public Claim getOne(String claimID) { //get the claim by user input id.
        for (Claim claim : claimList) {
            if (claim.getClaimID().equals(claimID)) {
                return claim;
            }
        }
        return null;
    }

    @Override
    public Set<Claim> getAll() { //get all the claims.
        Set<Claim> claimSet = new HashSet<>(claimList);
        List<Claim> claimList = new ArrayList<>(claimSet);
        Collections.sort(claimList, Comparator.comparing(Claim::getClaimID).reversed());
        Set<Claim> sortedClaims = new HashSet<Claim>(claimList);

        return sortedClaims;
    }

    @Override
    public Customer getCustomerById(String id) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public Customer getCustomerId(String id) {
        for (Customer customer : customerList)
        {
            if (customer.getId().equals(id))
            {
                return customer;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) { //add the customer.
        customerList.add(customer);
    }

    public Claim getClaimById(String claimID) { //get the claim by id.
        for (Claim claim : claimList) {
            if (claim.getClaimID().equals(claimID)) {
                return claim;
            }
        }
        return null;
    }
}
