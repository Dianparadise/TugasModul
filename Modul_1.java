package org.example;
import java.util.Scanner;

class login {
    String NIM;
    int opsi;
    String username = "admin";
    String password = "adm1n";
    String InpUsername;
    String InpPassword;

    void optionLogin() {
        Scanner inputData = new Scanner(System.in);
        do {
            System.out.println("==== Library System ===");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Choose option (1-3): ");
            opsi = inputData.nextInt();



            switch (opsi) {
                case (1):
                    System.out.println("Enter your NIM:");
                    NIM = inputData.nextLine();
                    int panjangNIM = NIM.length();

                    if (panjangNIM == 15) {
                        System.out.println("User found");
                    } else {
                        System.out.println("User not found");
                    }
                    break;
                case (2):
                    System.out.println("Enter your username: (admin)");
                    InpUsername = inputData.nextLine();
                    System.out.println("Enter your password: ");
                    InpPassword = inputData.nextLine();

                    if (username==InpUsername && password.equals(InpPassword)) {
                        System.out.println("Successfully Login as Admin");
                    } else {
                        System.out.println("Admin user not found");
                    }
                    break;
                case (3):
                    System.out.println("ADIOS");
                    break;
            }
        } while (opsi!=3);

    }
}

public class Modul_1 {
    public static void main(String[] args) {
        login objlogin = new login();
        objlogin.optionLogin();
    }
}

