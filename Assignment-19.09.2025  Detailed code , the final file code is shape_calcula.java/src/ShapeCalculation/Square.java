package ShapeCalculation;

public class Square extends Shape {
    private double side;
    
    public Square(double side) {
        super("Square");
        this.side = side;
    }
    
    @Override
    public double calculateArea() {
        return side * side;
    }
    
    @Override
    public double calculatePerimeter() {
        return 4 * side;
    }
    
    public double getSide() { return side; }
}