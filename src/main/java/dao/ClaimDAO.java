package dao;

import java.util.List;
import model.Claim;

public interface ClaimDAO {
    void submitClaim(Claim claim);
    List<Claim> getClaimsByRegId(int regId);
    List<Claim> getRecentClaims();
    void updateClaimStatus(int claimId, String status);
    List<Claim> getClaimsByUserId(int userId);
    int getClaimCountByRegId(int regId);
    List<Claim> getPendingClaims();
}
