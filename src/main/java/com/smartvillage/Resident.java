
package com.smartvillage;

public class Resident {

    // Encapsulation
    private int id;
    private String name;
    private int age;
    private String gender;
    private String village;
    private String phone;

    // Constructor
    public Resident(int id, String name, int age,
                    String gender, String village,
                    String phone) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.village = village;
        this.phone = phone;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getVillage() {
        return village;
    }

    public String getPhone() {
        return phone;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
