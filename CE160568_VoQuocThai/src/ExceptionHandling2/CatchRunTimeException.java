/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExceptionHandling2;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Vo Quoc Thai CE160568
 */
public class CatchRunTimeException {
    public static void main(String[] args) {
        int a;
        int b;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter number a: ");
                a = sc.nextInt();
                System.out.print("Enter number b: ");
                b = sc.nextInt();
                int result = a / b;
                System.out.println("Result a div b is " + result);
                break;
            } catch (InputMismatchException ex1) {
                System.out.println("Please enter an integer number!");
            } catch (ArithmeticException ex2){
                System.out.println("b must be different from 0!");
            }finally {
                sc.nextLine();
            }
        }
    }
}
