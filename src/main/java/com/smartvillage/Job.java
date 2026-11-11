
package com.smartvillage;

public class Job {

    private int jobId;
    private String jobTitle;
    private String organization;
    private String location;
    private double salary;
    private String description;

    // Constructor
    public Job(int jobId, String jobTitle,
               String organization, String location,
               double salary, String description) {

        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.organization = organization;
        this.location = location;
        this.salary = salary;
        this.description = description;
    }

    // Getters
    public int getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getOrganization() {
        return organization;
    }

    public String getLocation() {
        return location;
    }

    public double getSalary() {
        return salary;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
