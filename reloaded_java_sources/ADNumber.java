public class ADNumber {

    private double _value = 0.0;
    private double _derivative = 0.0;

    public ADNumber(double x, double d) {
        _value = x;
        _derivative = d;
    }

    public String toString() {
        return "ADNumber(value = " + _value + ", deriv = " + _derivative + ")"; 
    }
    
    public static ADNumber constant(double x) {
        return new ADNumber(x, 0.0);
    }

    public static ADNumber derivative(double x) {
        return new ADNumber(x, 1.0);
    }
    
    public double getValue() {
        return _value;
    }

    public double getDerivative() {
        return _derivative;
    }
}
