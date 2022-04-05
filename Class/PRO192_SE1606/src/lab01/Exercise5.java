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
public class Exercise5 {
    public static double Div(int a, int b)
    {
        if(b == 0)
        {
            System.out.println("b can't equal to 0");
        }
        return (a*1.0)/b;
    }
    public static int Add(int a, int b)
    {
        return a +b;
    }
    public static int Sub(int a, int b)
    {
        return a - b;
    }
    public static int Mul(int a, int b)
    {
        return a * b;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("-----Calculator-----");
        Scanner sc = new Scanner(System.in);
        int a = 0, b = 0;
        String chose = "";
        boolean isVaild = true;
        do {            
            try {
                System.out.print("Enter a: ");
                a = sc.nextInt();
            } catch (Exception e) {
                System.out.println("The number must be a integer number");
            }
            try {
                System.out.print("Enter b: ");
                b = sc.nextInt();
            } catch (Exception e) {
                System.out.println("The number must be a integer number");
            }
            System.out.print("Enter operator: ");
            chose = sc.nextLine();
            switch (chose){
                case"+":
                    int add = Add(a,b);
                    System.out.println("a + b = "+ add);
                    break;
                case"-":
                    int sub = Sub(a,b);
                    System.out.println("a + b = "+ sub);
                    break;
                case"*":
                    int mul = Mul(a,b);
                    System.out.println("a + b = "+ mul);
                    break;
                case"\\":
                    double div = Div(a,b);
                    System.out.println("a + b = "+ div);
                    break;
                    default:
                        System.out.println("Error");
                          
                    
            }
        } while (isVaild == false);
        
        
        
        
    }
}
