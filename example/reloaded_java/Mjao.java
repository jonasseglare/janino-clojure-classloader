import clojure.lang.PersistentVector;
import java.util.ArrayList;

public class Mjao {
    public static int magicOp(int x) {
        return x + 100000;
    }

    public static ADNumber getX() {
        return new ADNumber(3, 4);
    }
    
    public static ADNumber getY() {
        return new ADNumber(4, 5);
    }

    public static ADNumber add(ADNumber x, ADNumber y) {
        return new ADNumber(x.getValue() + y.getValue(), x.getDerivative() + y.getDerivative());
    }
}
