package Module;
/**
 * @Author Han Duc Khang - s3986602
 */
import java.util.Set;

public interface ClaimProcess {
    void add(Claim claim);
    void update(Claim claim);
    void delete(Claim claim);
    Claim getOne(String claimID);
    Set<Claim> getAll();
    Customer getCustomerById(String id);
}
