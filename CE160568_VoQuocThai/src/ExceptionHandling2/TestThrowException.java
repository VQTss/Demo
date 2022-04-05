
import ExceptionHandling2.CustomException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 
package ExceptionHandling2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vo Quoc Thai CE160568
 */
public class TestThrowException {
     int number;
    void setNumber(int number) throws  CustomException
    {
        if (number <= 0)
        {
            throw new CustomException("Number must be greater than 0");
        }
        this.number = number;
    }
    public static void main(String[] args) {
        TestThrowException test = new TestThrowException();
        try {
            test.setNumber(-2);
        }catch (CustomException ex)
        {
            Logger.getLogger(TestThrowException.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
