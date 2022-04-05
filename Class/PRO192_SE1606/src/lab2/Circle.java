
package lab2;

/**
 *
 * @author thaiq    
 */
public class Circle {
    private int radius; // 
    // Constructor
    public Circle(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    public double getPerimeter()
    {
        return 3.14 * 2 * this.radius;
    }
    public double getArea()
    {
        return 3.14 * this.radius * this.radius;
    }   
}
