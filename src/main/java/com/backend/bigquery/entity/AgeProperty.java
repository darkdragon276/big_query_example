package com.backend.bigquery.entity;

public class AgeProperty {
    private long age;
    private double health;
    private double commitment;
    private double perceptiveness;
    private double dexterity;
    private double sociality;
    private double goodness;
    private double strength;
    private double openmindedness;
    private double actualEfficacy;

    public AgeProperty(long age, double health, double commitment, double perceptiveness, double dexterity, double sociality, double goodness, double strength, double openmindedness, double actualEfficacy) {
        this.age = age;
        this.health = health;
        this.commitment = commitment;
        this.perceptiveness = perceptiveness;
        this.dexterity = dexterity;
        this.sociality = sociality;
        this.goodness = goodness;
        this.strength = strength;
        this.openmindedness = openmindedness;
        this.actualEfficacy = actualEfficacy;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getCommitment() {
        return commitment;
    }

    public void setCommitment(double commitment) {
        this.commitment = commitment;
    }

    public double getPerceptiveness() {
        return perceptiveness;
    }

    public void setPerceptiveness(double perceptiveness) {
        this.perceptiveness = perceptiveness;
    }

    public double getDexterity() {
        return dexterity;
    }

    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }

    public double getSociality() {
        return sociality;
    }

    public void setSociality(double sociality) {
        this.sociality = sociality;
    }

    public double getGoodness() {
        return goodness;
    }

    public void setGoodness(double goodness) {
        this.goodness = goodness;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getOpenmindedness() {
        return openmindedness;
    }

    public void setOpenmindedness(double openmindedness) {
        this.openmindedness = openmindedness;
    }

    public double getActualEfficacy() {
        return actualEfficacy;
    }

    public void setActualEfficacy(double actualEfficacy) {
        this.actualEfficacy = actualEfficacy;
    }
}
