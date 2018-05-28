package com.platforma.concedii.domain;

import com.platforma.concedii.util.HolidayStates;

import java.time.LocalDate;

public class ReportLine {

    private String firstName;
    private String lastName;
    private LocalDate startDate;
    private LocalDate endDate;
    private HolidayStates status;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public HolidayStates getStatus() {
        return status;
    }

    public void setStatus(HolidayStates status) {
        this.status = status;
    }
}
