import java.util.HashMap;

public class DiscountPercentages {

    public static double get(BookSetType typesOfBooks) {
        return discountPercentages().get(typesOfBooks);
    }

    private static HashMap<BookSetType, Double> discountPercentages() {
        HashMap<BookSetType, Double> percentagesPerNumberOfBookTypes = new HashMap<BookSetType, Double>();
        percentagesPerNumberOfBookTypes.put(BookSetType.None, 1.00);
        percentagesPerNumberOfBookTypes.put(BookSetType.One, 1.00);
        percentagesPerNumberOfBookTypes.put(BookSetType.Two, 0.95);
        percentagesPerNumberOfBookTypes.put(BookSetType.Three, 0.90);
        percentagesPerNumberOfBookTypes.put(BookSetType.Four, 0.80);
        percentagesPerNumberOfBookTypes.put(BookSetType.Five, 0.75);
        return percentagesPerNumberOfBookTypes;
    }
}
