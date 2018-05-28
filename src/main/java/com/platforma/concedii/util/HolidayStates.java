package com.platforma.concedii.util;

public enum HolidayStates {

    PENDING,
    APPROVED,
    REJECTED;

    public static HolidayStates getRoleByString(String s) {
        switch (s) {
            case "PENDING":
                return HolidayStates.PENDING;
            case "APPROVED":
                return HolidayStates.APPROVED;
            case "REJECTED":
                return HolidayStates.REJECTED;
        }
        return null;
    }

}
