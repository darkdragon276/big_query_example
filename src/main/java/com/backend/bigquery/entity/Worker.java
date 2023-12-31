package com.backend.bigquery.entity;

public class Worker {

    private long id;
    private String firstName;
    private String lastName;
    private long age;
    private  String sex;
    private  String shift;
    private  String team;
    private  String role;
    private double health;
    private double sociality;
    private long unEfficacyDate;
    private long efficacyDate;
    private double actualEfficacy;

    public Worker(long id, double health, double sociality, long unEfficacyDate, long efficacyDate, double actualEfficacy) {
        this.id = id;
        this.health = health;
        this.sociality = sociality;
        this.unEfficacyDate = unEfficacyDate;
        this.efficacyDate = efficacyDate;
        this.actualEfficacy = actualEfficacy;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getSociality() {
        return sociality;
    }

    public void setSociality(double sociality) {
        this.sociality = sociality;
    }

    public long getUnEfficacyDate() {
        return unEfficacyDate;
    }
    public long getEfficacyDate() {
        return efficacyDate;
    }

    public double getActualEfficacy() {
        return actualEfficacy;
    }
    public void setUnEfficacyDate(long unEfficacyDate) {
        this.unEfficacyDate = unEfficacyDate;
    }

    public void setEfficacyDate(long efficacyDate) {
        this.efficacyDate = efficacyDate;
    }

    public void setActualEfficacy(long actualEfficacy) {
        this.actualEfficacy = actualEfficacy;
    }

    public Worker(long id, String firstName, String lastName, long age, String sex, String shift, String team, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.shift = shift;
        this.team = team;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
