package model;

import java.util.Date;


public class Claim {
    private int claimId;
    private int regId;
    private Date dateOfClaim;
    private String description;
    private String status;
    
    public Claim() {
    	
    }
    public Claim(int claimId, int regId, Date dateOfClaim, String description, String status) {
        this.claimId = claimId;
        this.regId = regId;
        this.dateOfClaim = dateOfClaim;
        this.description = description;
        this.status = status;
    }

    public Claim(int regId, Date dateOfClaim, String description, String status) {
        this.regId = regId;
        this.dateOfClaim = dateOfClaim;
        this.description = description;
        this.status = status;
    }

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public Date getDateOfClaim() {
        return dateOfClaim;
    }

    public void setDateOfClaim(Date dateOfClaim) {
        this.dateOfClaim = dateOfClaim;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "claimId=" + claimId +
                ", regId=" + regId +
                ", dateOfClaim=" + dateOfClaim +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
   
}
