package com.platforma.concedii;

import com.platforma.concedii.service.UserService;

public class Application {

    public static void main(String args[]) {

        System.out.println("Hello world!");
        System.out.println(UserService.getInstance().getUserById(1).getFirstName());

    }

}
