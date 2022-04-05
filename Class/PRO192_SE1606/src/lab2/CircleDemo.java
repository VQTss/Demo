
package lab2;

/**
 *
 * @author thaiq
 */
public class CircleDemo {
    
    public static void main(String[] args) {
        Circle c = new Circle(5);
        System.out.println(c.getRadius());
        System.out.printf("Perimeter is: %.2f\n",c.getPerimeter());
        System.out.printf("Area      is: %.2f\n",c.getArea());
    }
}
