package com.backend.bigquery.entity;

public class EfficacyDayInWeek {
    private long dayInWeek; // (0 = Monday, .... 6 = Sunday)
    private double actualEfficacy;

    public EfficacyDayInWeek(long dayInWeek, double actualEfficacy) {
        this.dayInWeek = dayInWeek;
        this.actualEfficacy = actualEfficacy;
    }

    public long getDayInWeek() {
        return dayInWeek;
    }

    public void setDayInWeek(long dayInWeek) {
        this.dayInWeek = dayInWeek;
    }

    public double getActualEfficacy() {
        return actualEfficacy;
    }

    public void setActualEfficacy(double actualEfficacy) {
        this.actualEfficacy = actualEfficacy;
    }
}
