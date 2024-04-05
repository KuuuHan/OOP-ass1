package source_code;

import java.util.ArrayList;
import java.util.List;

public class ClaimProcessManager implements ClaimProcess{
    private List<Claim> claims;
    public ClaimProcessManager(){
        this.claims = new ArrayList<>();
    }

    @Override
    public void add(Claim claim) {
        claims.add(claim);
    }

    @Override
    public void update(Claim claim) {
        for (int i  = 0; i < claims.size(); i++){
            if (claims.get(i).getClaimID().equals(claim.getClaimID())){
                claims.set(i, claim);
                break;
            }
        }
    }

    @Override
    public void delete(Claim claim) {
         claims.removeIf(c -> c.getClaimID().equals(claim.getClaimID())); //remove all Claim objects, which claimID matches the claimID passed to the delete method.
    }

    @Override
    public Claim getOne(String claimID) {
        for (Claim claim : claims) {
            if (claim.getClaimID().equals(claimID)) {
                return claim;
            }
        }
        return null;
    }

    @Override
    public List<Claim> getAll() {
        return new ArrayList<>(claims);
    }
}
