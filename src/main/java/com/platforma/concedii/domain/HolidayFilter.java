package com.platforma.concedii.domain;

import java.util.Date;
import java.util.List;

public class HolidayFilter {

    private List<String> statusList;
    private List<Integer> userIdList;
    private Date minDate;
    private Date maxDate;
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

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }
}
