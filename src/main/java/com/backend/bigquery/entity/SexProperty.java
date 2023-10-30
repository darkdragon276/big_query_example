package com.backend.bigquery.entity;

public class SexProperty {
    private String sex;
    private double coworkerSameSexRatio;
    private double actualEfficacy;

    public SexProperty(String sex, double coworkerSameSexRatio, double actualEfficacy) {
        this.sex = sex;
        this.coworkerSameSexRatio = coworkerSameSexRatio;
        this.actualEfficacy = actualEfficacy;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getCoworkerSameSexRatio() {
        return coworkerSameSexRatio;
    }

    public void setCoworkerSameSexRatio(double coworkerSameSexRatio) {
        this.coworkerSameSexRatio = coworkerSameSexRatio;
    }

    public double getActualEfficacy() {
        return actualEfficacy;
    }

    public void setActualEfficacy(double actualEfficacy) {
        this.actualEfficacy = actualEfficacy;
    }
}
