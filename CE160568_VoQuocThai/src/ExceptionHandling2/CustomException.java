/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExceptionHandling2;

/**
 *
 * @author thaiq
 */
public class CustomException extends Exception {
     String message;

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
