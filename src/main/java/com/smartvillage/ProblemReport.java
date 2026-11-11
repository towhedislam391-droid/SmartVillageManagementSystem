
package com.smartvillage;

public class ProblemReport {

    private int reportId;
    private String residentName;
    private String category;
    private String location;
    private String description;
    private String status;

    // Constructor
    public ProblemReport(int reportId, String residentName,
                         String category, String location,
                         String description, String status) {

        this.reportId = reportId;
        this.residentName = residentName;
        this.category = category;
        this.location = location;
        this.description = description;
        this.status = status;
    }

    // Getters
    public int getReportId() {
        return reportId;
    }

    public String getResidentName() {
        return residentName;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setCategory(String category) {
        this.category = category;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
