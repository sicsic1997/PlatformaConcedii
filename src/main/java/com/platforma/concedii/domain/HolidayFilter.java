package com.platforma.concedii.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class HolidayFilter {

    private List<String> statusList;
    private List<Integer> userIdList;
    private LocalDate minDate;
    private LocalDate maxDate;
    private boolean useDateRange;

    public boolean isUseDateRange() {
        return useDateRange;
    }

    public void setUseDateRange(boolean useDateRange) {
        this.useDateRange = useDateRange;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public List<Integer> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Integer> userIdList) {
        this.userIdList = userIdList;
    }

    public LocalDate getMinDate() {
        return minDate;
    }

    public void setMinDate(LocalDate minDate) {
        this.minDate = minDate;
    }

    public LocalDate getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(LocalDate maxDate) {
        this.maxDate = maxDate;
    }
}
