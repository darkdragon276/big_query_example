package com.backend.bigquery.entity;

public class Timesheets {
    private long id;
    private long workingDay;
    public Timesheets(long id, long workingDay) {
        this.id = id;
        this.workingDay = workingDay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(long workingDay) {
        this.workingDay = workingDay;
    }
}
