package com.platforma.concedii.dto;

import com.platforma.concedii.util.HolidayStates;

import java.time.LocalDate;

public final class HolidayDTO {

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int userId;
    private String firstName;
    private String lastName;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public HolidayStates getStatus() {
        return status;
    }

    public void setStatus(HolidayStates status) {
        this.status = status;
    }
}
