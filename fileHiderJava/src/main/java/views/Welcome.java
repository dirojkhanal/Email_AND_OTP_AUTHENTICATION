package views;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Welcome {
    public void welcomeScreen() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("welcome to the app");
        System.out.println("press 1 to login");
        System.out.println("press 2 to signup");
        System.out.println("press 0 to exit");
        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        switch(choice){
            case 1->login();
            case 2 -> signUp();
            case 0 ->System.exit(0);
        }

    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter email: ");
        String email = sc.nextLine();
        try{
            if(UserDAO.isExists(email)) {
                String genOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email,genOTP);
                System.out.println("Enter the OTP:");
                String otp = sc.nextLine();
                if(otp.equals(genOTP)) {
                    new UserView(email).home();

                }
                else{
                    System.out.println("wrong otp");
                }
            }
            else {
                System.out.println("User not Found");
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    private void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name : ");
        String name = sc.nextLine();
        System.out.println("Enter Email: ");
        String email = sc.nextLine();
        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email,genOTP);
        System.out.println("Enter the OTP: ");
        String otp = sc.nextLine();
        if(otp.equals(genOTP)) {
            User user = new User(name,email);
            int response = UserService.saveUser(user);
            switch (response) {
                case 0 -> System.out.println("User Registered.");
                case 1 -> System.out.println("User Already Existed.");
            }
        }
        else{
            System.out.println("wrong OTP");
        }

    }

}
