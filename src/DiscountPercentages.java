import java.util.HashMap;

public class DiscountPercentages {

    public static double get(int typesOfBooks) {
        return discountPercentages().get(typesOfBooks);
    }

    private static HashMap<Integer, Double> discountPercentages() {
        HashMap<Integer, Double> percentagesPerNumberOfBookTypes = new HashMap<Integer, Double>();
        percentagesPerNumberOfBookTypes.put(1, 1.00);
        percentagesPerNumberOfBookTypes.put(2, 0.95);
        percentagesPerNumberOfBookTypes.put(3, 0.90);
        return percentagesPerNumberOfBookTypes;
    }
}
