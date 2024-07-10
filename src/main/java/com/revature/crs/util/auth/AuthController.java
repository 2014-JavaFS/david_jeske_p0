package com.revature.crs.util.auth;

import java.util.Scanner;

public class AuthController {

    private final Scanner scanner;
    private final AuthService authService;

    public AuthController(Scanner scanner, AuthService authService){
        this.scanner=scanner;
        this.authService=authService;
    }



}
