/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab01;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author thaiq
 */
public class Exercise4 {
     public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("----BANK ACCOUNT----");
        Scanner sc = new Scanner(System.in);
        double interests = 0;
        int desposits = 0;
        int terms = 0;
        double rate = 0;
        boolean isValid = true;
        do {
            try {
                isValid = true;
                System.out.print("Please enter desposits: ");
                desposits = sc.nextInt();
                if (desposits <= 0) {
                    System.out.println("The desposits must be greater than 0!");
                    isValid = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("The desposits must be a number! The desposits must be greater than 0!");
                isValid = false;
                sc.nextLine();
            }
        } while (isValid == false);
        do {
            try {
                isValid = true;
                System.out.print("Please enter terms: ");
                terms = sc.nextInt();
                if (terms < 0) {
                    System.out.println("The terms must be equal or greater than 0!");
                    isValid = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("The terms must be a number! The terms must be greater than 0!");
                isValid = false;
                sc.nextLine();
            }
        } while (isValid == false);
        do {
            try {
                isValid = true;
                System.out.print("Please enter rates: ");
                rate = sc.nextDouble();
                if (rate <= 0) {
                    System.out.println("The rates must be greater than 0!");
                    isValid = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("The rates must be a number! The rates must be greater than 0!");
                isValid = false;
                sc.nextLine();
            }
        } while (isValid == false);
        double ratesByMonth = rate / 12;
        interests = desposits * terms * (ratesByMonth / 100.0);
        double total = interests + desposits;
        System.out.println("The total money: " + total + " VND");
    }
}
