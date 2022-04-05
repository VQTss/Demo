/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExceptionHandling2;

import java.util.Scanner;

/**
 *
 * @author Vo Quoc Thai CE160568
 */
public class TestAssertion {
     public static void main(String[] args) {
        int a;
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter number a: ");
        a = sc.nextInt();
        assert (a > 0 && a < 10) :
                "The number must be greater than 0 and less than 10";
        System.out.println("Number is ok");

    }
}
