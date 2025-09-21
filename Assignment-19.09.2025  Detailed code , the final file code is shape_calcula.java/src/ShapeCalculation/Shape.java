package ShapeCalculation;

public abstract class Shape {
    protected String name;
    
    public Shape(String name) {
        this.name = name;
    }
    
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
    
    public String getName() {
        return name;
    }
}