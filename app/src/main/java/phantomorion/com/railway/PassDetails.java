package phantomorion.com.railway;

public class PassDetails {
    Boolean approvedCollege;
    Boolean approvedRailway;
    String source,  type, issued, expiry,gender,name;

    public PassDetails() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PassDetails(Boolean approvedCollege, Boolean approvedRailway, String source, String type, String issued, String expiry, String gender, String name) {
        this.approvedCollege = approvedCollege;
        this.approvedRailway = approvedRailway;
        this.source = source;
        this.name=name;
        this.type = type;
        this.issued = issued;
        this.expiry = expiry;
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public Boolean getApprovedCollege() {
        return approvedCollege;
    }

    public void setApprovedCollege(Boolean approvedCollege) {
        this.approvedCollege = approvedCollege;
    }

    public Boolean getApprovedRailway() {
        return approvedRailway;
    }

    public void setApprovedRailway(Boolean approvedRailway) {
        this.approvedRailway = approvedRailway;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
}
