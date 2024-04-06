package source_code;

import java.io.*;

/**
 * @Author Han Duc Khang - s3986602
 */
public class ClaimController {
    private ClaimProcess claimProcess;
    private SystemView systemView;
    public ClaimController(ClaimProcess claimProcess, SystemView systemView) {
        this.claimProcess = claimProcess;
        this.systemView = systemView;
    }

    public void addClaim(Claim claim) {
        claimProcess.add(claim);
    }

    public void updateClaim(Claim claim) {
        claimProcess.update(claim);
    }
    public void deleteClaim(Claim claim) {
        claimProcess.delete(claim);
    }
    public void viewClaim(String claimID) {
        Claim claim = claimProcess.getOne(claimID);
        systemView.display(claim);
    }

    public void viewAllClaims() {
        systemView.displayAll(claimProcess.getAll());
    }


    public void saveToFile(String fileName){
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File(fileName), true /* append = true */))) {
            for (Claim claim : claimProcess.getAll()) {
                writer.println(claim.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to save to file: " + e.getMessage());
        }
    }

    public void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String claimID = parts[0];
                String claimDate = parts[1];
                double claimAmount = Double.parseDouble(parts[2]);
                String claimStatus = parts[3];
                String insuredPerson = parts[4];
                String bankName = parts[5];
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to read the file: " + e.getMessage());
        }
    }

}
