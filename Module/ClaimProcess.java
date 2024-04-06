package Module;
/**
 * @Author Han Duc Khang - s3986602
 */
import java.util.List;

public interface ClaimProcess {
    void add(Claim claim);
    void update(Claim claim);
    void delete(Claim claim);
    Claim getOne(String claimID);
    List<Claim> getAll();
}
