/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab01;

import java.util.Scanner;

/**
 *
 * @author thaiq
 */
public class NameCard {

    public static void main(String[] args) {

        System.out.println("---WELCOME TO NAMECARD SOFTWARE---");
        Scanner input = new Scanner(System.in);
        String name = "";
        int age = 0;
        double height = 0;
        do {
            System.out.print("Please enter your name: "); // show message
            name = input.nextLine().trim();
            if (name.equals("")) {
                System.out.println("Your name can't be emtpy!");
            }
        } while (name.equals(""));
        boolean isVaild = true;
        do {
            try {
                isVaild = true;
                System.out.print("Please enter your age: ");
                age = input.nextInt();
                if (age <= 0 || age > 150) {
                    System.out.println("Your age must from 1 to 150!");
                    isVaild = false;
                }
            } catch (Exception e) {
                System.out.println("The age must a number! Age must be from 1 to 150!");
                isVaild = false;
                input.nextLine();
            }
        } while (isVaild == false);
        do {            
            try {
                isVaild = true;
                System.out.print("Please enter your height: ");
                height = input.nextInt();
                if(height <= 0)
                {
                    System.out.println("Your height must be greater than 0!");
                    isVaild = false;
                }
            } catch (Exception e) {
                System.out.println("The height must be a number! Height must be greater than 0!");
                isVaild = false;
                input.nextLine();
            }
        } while (isVaild == false);
        System.out.println("- Your name :  " +name);
        System.out.println("-Your  age  :  " +age);
        System.out.printf("-Your height :  %.2f\n",height);
    }
}
