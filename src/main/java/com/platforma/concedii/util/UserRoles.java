package com.platforma.concedii.util;

public enum UserRoles {

    MAMANGER,
    EMPLOYEE,
    ADMIN;

    public static UserRoles getRoleByString(String s) {
        switch (s) {
            case "MANAGER":
                return UserRoles.MAMANGER;
            case "EMPLOYEE":
                return UserRoles.EMPLOYEE;
            case "ADMIN":
                return UserRoles.ADMIN;
        }
        return null;
    }

}
